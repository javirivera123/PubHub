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
	



	
	   $('.DeleteTag:button').click(function(){
		   if (confirm('Are you sure you want to delete tag from book?')) {
			    // call servlet to delete!
			   $.ajax({
				     url: "DeleteTag",
				     method: "POST",
				     data: {isbn13: $('#isbn13').val(), tag: $(this).val()}
				  }).done(function(res){
				     console.log(res);
				    //"Updated data successfully\n";
				    //IF ALL IS OK!!
				  });
			   
			   
			 var deletee = $(this).val();
			   $('#' + deletee).remove();
			   alert("deleted");
			} else {
			    // Do nothing!
			}
		    });

		    
	
});

