/**
 * 
 */
$(document).ready(function() {
 
	// remove any error messages after 3 seconds
	setTimeout(function(){
		$(".alert").fadeOut("slow");
	} ,3000);
	
	
	
	//setup dataTables
	$(".pubhub-datatable").DataTable({
		stateSave: true,
		lengthChange: false,
		info:false,
		language: {
			zeroRecords: "No items matched your search"
		}
		
	});
	
    // Script to Activate the Carousel
    $('.carousel').carousel({
//        interval: 5000 //changes the speed
    });

	
    
	$(".pubhub-simple-datatable").DataTable({
		paging: false,
		searching: false,
		info: false,
		ordering: false
		
	});	
	
	$('#Add').click(function(){
		$('.col-sm-5:last').after($('.col-sm-5:last').clone());
	     alert("The add was clicked.");
	});



	   $('#Delete').click(function(){
		    $('.col-sm-5:last').remove();
		    });
	   
	   $('#DeleteTag').click(function(){
		   if (confirm('Are you sure you want to delete tag from book?')) {
			    // call servlet to delete!
			   alert("deleted");
			} else {
			    // Do nothing!
				alert('not deleted');
			}
		    });

		    
		    $('#Retrieve').click(function(){
		        $('.col-sm-5 input:text').each(function(i,e){
		        alert($(e).val()); //Alerts all values individually
		        });
	    });
	
});

