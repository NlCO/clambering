function toggleFilter(e){
    $(e.target)
        .prev('.card-header')
        .find("i.fas")
        .toggleClass('fa-chevron-down fa-chevron-up')
}

$('#collapseFilterForm').on('hidden.bs.collapse', toggleFilter);
$('#collapseFilterForm').on('shown.bs.collapse', toggleFilter);

/* Affichage Map*/
function affichageMap(lat,lon) {
    var mymap = L.map('mapid').setView([lat, lon], 7);

    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
        maxZoom: 18,
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
            '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
            'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        id: 'mapbox.outdoors'
    }).addTo(mymap);

    L.marker([lat, lon]).addTo(mymap);
    L.popup();
}

