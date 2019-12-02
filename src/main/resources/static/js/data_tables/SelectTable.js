// used to hide all tables but the one selected by the user
function hideAllButOne(optionToShow){
    var options = ["mydatatable","Help Offers","Help Requests"]; // Options in the dropdown MAKE SURE TO UPDATE IF ADDING MORE OPTIONS
    for (var i = 0; i < options.length; i++) {
        var idPrefix = "#";
        var tableName = idPrefix.concat(options[i].replace(/ /g,"_").toLowerCase());

        var wrapperSuffix ="_wrapper";
        var wrapperName = tableName.concat(wrapperSuffix);

        if(options[i] == optionToShow){
            // show selected table
            $(wrapperName).show();
            console.log("showing ".concat(tableName))
        }else{
            // hide all tables besides selected table
            $(wrapperName).hide();
        }
    }
}


$(document).ready(function(){

    // hide all tables on startup besides user table
    $('#help_offers_wrapper').hide();
    $('#help_requests_wrapper').hide();


    // gets the currently selected table to search
    $("select.selectedSearchCategory").change(function(){
        var selectedCategory = $(this).children("option:selected").val();
        hideAllButOne(selectedCategory);
    });

});