let dropdown = $('#skills-dropdown');
const url = "localhost:8080/api/Skills/" ; // will need to be changed when moved to an actual server as opposed to running on a local machine

$( document ).ready(function() {
    cleanDropDown();
    populateDropDown();
});

function cleanDropDown(){
    dropdown.empty();
    dropdown.append('<option selected = true disabled>Please select your skills that are related to this offer</option>');  // add a default option to give user an idea of what to select in drop down
    dropdown.prop('selectedIndex',0);
}


function populateDropDown(){
    $.getJSON(url,function(data){
        $.each(data,function(key, entry){
            dropdown.append($('<option></option>').attr('value', entry).text(entry.name));
            })
        });
}