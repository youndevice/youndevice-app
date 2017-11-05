function usageFoundInDestinationWarning(data) {
    var html = "<h4>" + data.message + " </h4><ul class='list-unstyled'>" +
        $.map(data.data.destinations, function (item) {
            return "<li><a target='_blank' href='/destination/createEditDestination/" + item.id + "#otherDestinations'>" + item.name + "</a></li>";
        }).join("") + "</ul>";
    bootbox.dialog({
        message: html,
        buttons: {
            confirm: {
                label: 'OK',
                className: 'btn-success'
            }
        }
    });
}

function commonDestinationSuccessCallBack(data) {
    if (data.status) {
        showSuccessMsg(data.message);
        if (window.dataTable) {
            window.dataTable.ajax.reload();
        }
    } else {
        if (data.code == 301) {
            usageFoundInDestinationWarning(data);
        } else {
            showErrorMsg(data.message);
        }
    }
}