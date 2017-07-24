$(document).ready(function() {

  displayAllGames();

  function displayAllGames() {
    $.ajax({
      type: "GET",
      cache: false,
      dataType: "json",
      url: "/api/game",
      success: function (response) {
        if (response) {
          var html = "";
          $.each(response, function (i, it) {
            html = html.concat(["Name: ", it.title, "; State: ", it.state, "<br/>"].join(""));
          });
          $("#content").find(".js-content").html(html);
        }
      },
      error: processError
    });
  }

  function processError(response) {
    alert(response.responseText);
  }
});