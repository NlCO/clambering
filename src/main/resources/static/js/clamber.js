function toggleFilter(e){
    $(e.target)
        .prev('.card-header')
        .find("i.fas")
        .toggleClass('fa-chevron-down fa-chevron-up')
}

$('#collapseFilterForm').on('hidden.bs.collapse', toggleFilter);
$('#collapseFilterForm').on('shown.bs.collapse', toggleFilter);

