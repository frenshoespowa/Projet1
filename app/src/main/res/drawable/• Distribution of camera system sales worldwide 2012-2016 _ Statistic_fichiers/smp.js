$(".socialMediaBox .showPopup").on("click",function(d){d.preventDefault();var a=$("body").find(".showTooltip.hoverTooltip");if(a.length>0){a.remove()}else{$(".showTooltip").remove();var f=$(this).find(".popup-content").html();var k=$('<div class="showTooltip hoverTooltip hideOnClick animated fadeInRight">');k.append('<span class="floatRight close" style="top: 5px;right: 10px;position: absolute;">&times;</span>');k.append(f);$("body").append(k);var i=$(this).offset();var c=$(this).outerHeight(true);var j=$(this).outerWidth(true);var g=k.outerHeight();var b=k.outerWidth();k.css({top:i.top-5+"px",left:i.left+j+15+"px"})}});$("body").on("click",".showTooltip.hideOnClick",function(b){var a=$("body").find(".showTooltip.hoverTooltip");if(a.length>0){a.remove()}});