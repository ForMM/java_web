(function($){  
	var layerIndex = null;
	$(document).ajaxStart(function(){
		layerIndex = layer.open({type: 2,time: 8});	
    });

    $(document).ajaxStop(function(){
    	layer.close(layerIndex);
    });
})(jQuery); 