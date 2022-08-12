export default class StickersGenerator {
    constructor(urlImage, comment, fontColor, fontSize) {
        this.urlImage = urlImage;
        this.comment = comment;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
    }

    generate() {

        var stickerViewer = document.getElementById("sticker-viewer");

        if (this.urlImage.value != "" && this.comment.value != "") {

            var btnGenerateSticker = document.getElementById("btn-generateSticker");

            if (btnGenerateSticker.innerText === "Gerar") {

                let halfUrl = this.urlImage.value.split("/")[6];

                fetch(
                    `https://stickers-generator.herokuapp.com/tmdb/movies/generate-sticker/image-url=${halfUrl}&comment=${this.comment.value.replaceAll(" ", "-")}&font-color=${this.fontColor}&font-size=${this.fontSize}`,
                    {
                        method: "GET",
                    }
                )
                    .then((response) => response.json())
                    .then((json) => {
                        var encodeImage = json.bindataSticker;
                        stickerViewer.innerHTML = `
                        <a href="${"data:image/png;base64," + encodeImage}" download="sticker">
                            <img src="${"data:image/png;base64," + encodeImage}" />
                        </a>`;
                        btnGenerateSticker.innerText = "Limpar";
                    }).catch(() => {
                        alert("Ocorreu um erro ao gerar a figurinha :(");
                    });

            } else if (btnGenerateSticker.innerText === "Limpar") {
                stickerViewer.innerHTML = `
                <div id="image-placeholder"></div>
                <div id="frase-placeholder">
                  <p></p>
                </div>`;
                this.urlImage.value = "";
                document.getElementById("fontColor").value = "000000";
                this.comment.value = "";
                btnGenerateSticker.innerText = "Gerar";
            }

        } else {
            if (this.comment.value === "") { this.comment.style.border = "1px solid red"; }
            if (this.urlImage.value === "") { this.urlImage.style.border = "1px solid red"; }
        }

    }

}