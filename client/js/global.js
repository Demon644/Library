let serverURL = 'http://localhost:8080/';
function logout() {
    window.localStorage.removeItem('auth_token');
    location.reload();
  }

function loadBook() {
    $.ajax({
        url: serverURL + 'book/',
        method: 'GET',
        contentType: 'application/json',
        complete: function (data) {
            if (data.status == 200) {
                console.log(data)
                let total = data.responseJSON.length;
                
                $.each(data.responseJSON, function(key, value) {
                    $('#books > .card-deck').append( // col-auto mb-3 mx-auto
                        // <div class="col-auto mb-3">
                        // style="width: 21.3rem;"
                        // col-lg-3
                        `
                        <div class="col-sm-6 col-md-3">
                            <div class="card mb-4" style="width: 28rem;">
                                <img class="card-img-top" src="14.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title">${value.name}</h5>
                                    <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
                                    <a href="#" class="btn btn-primary">Go somewhere</a>
                                </div>
                            </div>
                        </div>
                        `
                        )
                });
            }}
      });
};


