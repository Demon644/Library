let serverURL = 'http://localhost:8080/';
let globalBookId = 0;
let userLoginParsed = '';

function logout() {
    window.localStorage.removeItem('auth_token');
    location.reload();
}

function getLoginFromJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64)).sub;
};

function loadBook() {
    $.ajax({
        url: serverURL + 'book/',
        method: 'GET',
        contentType: 'application/json',
        complete: function (data) {
            if (data.status == 200) {
                console.log(data)
                let total = data.responseJSON.length;

                $.each(data.responseJSON, function (key, value) {
                    $('#books > .card-deck').append( // col-auto mb-3 mx-auto
                        // <div class="col-auto mb-3">
                        // style="width: 21.3rem;"
                        // col-lg-3
                        `
                        <div>
                        <div class="card mb-4 shadow">
                        <img class="card-img-top img-fluid" src="${serverURL}book/image?file=${value.image}" alt="Card image cap">
              
                          
              
                        <ul class="list-group list-group-flush">
              
                          <div class="card-body list-group-item">
                            <h5 class="card-title text-center">${value.name}</h5>
                            <h6 class="card-subtitle mb-2 text-muted text-center">${value.category.category}</h6>
                            <p class="card-text">${value.author.name}</p>
                            <p class="card-text ">Language: ${value.language}</p>
                            <p class="card-text">${value.short_info}</p>
                            <p class="card-text text-center" style="border-radius: 25px; background: #EDF5E1;">${value.price} $</p>
              
                            <div class="text-center">
                              <a id="book-${value.id}" href="#" class="btn btn-primary shadow" data-toggle="modal"
                                data-target="#orderInfo">Buy</a>
                            </div>
              
                          </div>
              
                        </ul>
              
                      </div>
                        
                        `
                    )
                });
            }
        }
    });
};