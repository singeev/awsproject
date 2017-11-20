$(document).ready(main);

function main() {
    $('.btn-collapse').click(function (e) {
        e.preventDefault();
        var $this = $(this);
        var $collaps = $this.closest('.collapse-group').find('.collapse');
        $collaps.collapse('toggle');
    });
}