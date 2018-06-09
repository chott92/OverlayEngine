$(function(){
   $.get('/api/hello', function(data){
       $('#target').text('Greeting for ' + data.name + ': ' + data.message);
   });
});