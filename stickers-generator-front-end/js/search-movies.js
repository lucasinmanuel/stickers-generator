export default class ListMovies {

  constructor(search, pageNumber) {
    this.search = search.replaceAll(" ", "-");
    this.pageNumber = pageNumber;
  }

  async getListMovies() {

    let movies;

    if (this.search != "popular") {
      await fetch(
        `https://stickers-generator.herokuapp.com/tmdb/movies/search=${this.search}&page-number=${this.pageNumber}`,
        {
          method: "GET",
        }
      )
        .then((response) => response.json())
        .then((json) => {
          movies = json;
        })

    } else {

      await fetch(
        `https://stickers-generator.herokuapp.com/tmdb/movies/list-type=${this.search}&page-number=${this.pageNumber}`,
        {
          method: "GET",
        }
      )
        .then((response) => response.json())
        .then((json) => {
          movies = json;
        })

    }

    return movies;

  }
}
