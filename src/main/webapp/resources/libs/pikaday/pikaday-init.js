var IDs = [];
var dateTimeFields = $('input[class="date-time-field"]').each(function() {
  IDs.push(this.id);
});

for (var i = 0; i < IDs.length; i++) {
  var id = IDs[i];

  var picker = new Pikaday({
    field: document.getElementById(id),
    format: 'DD.MM.YYYY',
    onSelect: function() {
      console.log(this.getMoment().format('DD.MM.YYYY'));
    }
  });
}