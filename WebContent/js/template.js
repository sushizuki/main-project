$(document).ready(function(){
    
	//Homepage Slider
    var options = {
        nextButton: false,
        prevButton: false,
        pagination: true,
        animateStartingFrameIn: true,
        autoPlay: true,
        autoPlayDelay: 2000,
        preloader: true
    };
    
    var mySequence = $("#sequence").sequence(options).data("sequence");
    
});