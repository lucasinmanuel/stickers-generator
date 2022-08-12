import SearchMovies from "./search-movies.js";
import AddComment from "./addComment.js";
import StickersGenerator from "./stickersGenerator.js";

//IMPEDINDO SUBMITS QUE DAR REFLESH NA PÁGINA
let forms = document.querySelectorAll("form");
for (let i = 0; i < forms.length; i++) {
    forms[i].addEventListener("submit", function (e) {
        e.preventDefault();
    })
}

//REQUISIÇÃO INICIAL PARA GERAR FILMES POPULARES DO MOMENTO
let searchMovies = new SearchMovies("popular", "1");
let popularMovies = searchMovies.getListMovies();

var areaFilmes = document.getElementById("filmes-wrapper");

popularMovies.then((promisse) => {

    promisse.results.forEach((value) => {
        areaFilmes.innerHTML += `
        <div>
            <img id="${value.id}" title="${value.title}" alt="${value.title}" width="100%" src="https://image.tmdb.org/t/p/w500${value.poster_path}" />
        </div>
    `;
    });

    clickImages();

})

//REQUISIÇÃO COM BASE NAS INFORMAÇÕES DA BARRA DE PESQUISA
//EVENTO INICIADO COM O CLIQUE DO MOUSE NA LUPA
let searchBtn = document.getElementById("btn-search");
searchBtn.addEventListener("click", function () {

    var searchInput = document.getElementById("search");
    let searchBar = new SearchMovies(searchInput.value, "1");
    searchBar.getListMovies().then((promisse) => {

        var areaFilmes = document.getElementById("filmes-wrapper");
        areaFilmes.innerHTML = "";

        promisse.results.forEach((value) => {
            areaFilmes.innerHTML += `
                <div>
                    <img id="${value.id}" title="${value.title}" alt="${value.title}" width="100%" src="https://image.tmdb.org/t/p/w500${value.poster_path}" />
                </div>
            `;
        });

        clickImages();

    });

});

function clickImages() {

    var imageInput = document.getElementById("urlImg");

    var filmesArray = document.querySelectorAll("#filmes-wrapper img");
    filmesArray.forEach((v, index) => {

        filmesArray[index].addEventListener("click", function () {

            window.scroll(0, 0);

            imageInput.style.border = "0";
            imageInput.value = v.src;

            //O ELEMENTO BUSCADO PELO ID E EXCLUIDO E REPOSTO, ENTÃO É NECESSÁRIO CRIAR A VARIAVEL EM CADA CLICK
            var imagePlaceholder = document.getElementById("image-placeholder");
            imagePlaceholder.innerHTML = `<img src="${v.src}" alt="${v.title}" />`;

        });

    });

}

var colorInput = document.getElementById("fontColor");
colorInput.addEventListener("blur", () => {
    var fraseNoSticker = document.querySelector("#frase-placeholder p");
    fraseNoSticker.style.color = colorInput.value;
});

//PASSA O TEXTO DO INPUT PARA A FIGURINHA AO DIGITAR
var commentInput = document.getElementById("comment");
commentInput.addEventListener("keyup", () => {

    var fraseNoSticker = document.querySelector("#frase-placeholder p");
    var addComment = new AddComment(commentInput, fraseNoSticker);
    addComment.add();

});

//PASSA O TEXTO DO INPUT PARA A FIGURINHA AO COLAR
commentInput.addEventListener("input", () => {

    var fraseNoSticker = document.querySelector("#frase-placeholder p");
    var addComment = new AddComment(commentInput, fraseNoSticker);
    addComment.add();

});

//FAZ O FETCH PARA A APLICAÇÃO JAVA NA NUVEM
var btnGenerateSticker = document.getElementById("btn-generateSticker");
btnGenerateSticker.addEventListener("click", () => {

    var imageInput = document.getElementById("urlImg");
    var fontColor = hexToRgb(colorInput.value).toString();
    var fontSize = document.getElementById("fontSize").value;

    var stickersGenerator = new StickersGenerator(imageInput, commentInput, fontColor, fontSize);
    stickersGenerator.generate();

});

//CONVERSOR DE HEXADECIMAL PARA RGB 
const hexToRgb = hex =>
    hex.replace(/^#?([a-f\d])([a-f\d])([a-f\d])$/i
        , (m, r, g, b) => '#' + r + r + g + g + b + b)
        .substring(1).match(/.{2}/g)
        .map(x => parseInt(x, 16))