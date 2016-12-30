$(document).ready(function() {
		initVisitor();
		initLocalTimer();
		dialogIssueDel = $( "#dialog-del-issue" ).dialog({
		      autoOpen: false,
		      modal: true,
		      buttons: {
	    	  	"del": function () {
	    	  		delIssue($("#delIssueId").val());
	    	  	},
		        Cancel: function() {
		        	dialogIssueDel.dialog( "close" );
		        }
		      },
		      close: function() {
		    	  dialogIssueDel.dialog( "close" );
		      }
		    });
		$( "#dialog-del-issue" ).on( "click", function() {
			dialogIssueDel.dialog( "open" );
	    });
		
		dialogDeliverDel = $( "#dialog-del-deliver" ).dialog({
		      autoOpen: false,
		      modal: true,
		      buttons: {
	    	  	"del": function () {
	    	  		delDeliver($("#delDeliverId").val());
	    	  	},
		        Cancel: function() {
		        	dialogDeliverDel.dialog( "close" );
		        }
		      },
		      close: function() {
		    	  dialogDeliverDel.dialog( "close" );
		      }
		    });
		$( "#dialog-del-deliver" ).on( "click", function() {
			dialogDeliverDel.dialog( "open" );
	    });
	});

	function initVisitor() {
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/issues/getVisitor",
			dataType : "json"
		}).done(function(data) {
			$("#welcomeUser").text(data.realName);
			$("#userRole").val(data.role);
			initMain();
		});
	}
	function initLocalTimer() {
		var myVar = setInterval(function(){ myTimer() }, 0);
		function myTimer() {
		    var d = new Date();
		    var t = d.toLocaleTimeString();
		    var year = d.getFullYear();
		    var month = d.getMonth() + 1;
		    var day = d.getDate();
		    var localTimer = month + "/" + day + "/"  + year+ " " + t
		    $("#localTimer").html(localTimer);
		}
	}
	function initTooltip() {
		$( ".ellipsisSummary a" ).hover(function() {
			$(this).attr("title",$(this).html());
			$(this).addClass("clearFloat");
			$(this).parent().tooltip();
		});
	}
	function initMain() {
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/issues/getList",
			dataType : "json"
		}).done(function(msg) {
			var issueTr = '<tr>' + 
				'<td width="2%" class="titleBold">No</td>' + 
				'<td width="10%" class="titleBold">IssueType</td>' + 
				'<td width="10%" class="titleBold">Summary</td>' + 
				'<td width="30%" class="titleBold">Description logs</td>' + 
				'<td width="10%" class="titleBold">Solution</td>' + 
				'<td width="10%" class="titleBold">Fixed By</td>' + 
				'<td width="10%" class="titleBold">Input Date</td>' + 
				'<td width="10%" class="titleBold">Add By</td>' + 
				'<td width="8%" class="titleBold"><span class="operation">Oper</span>' + 
				'<div class="ui-state-default ui-corner-all iconAdd"><a id="create-issue" class="ui-icon ui-icon-plusthick" title="add" href="javascript:void(0);"></a></div>' + 
				'</td></tr>';
			$("#list").find("table").find("thead").append(issueTr);
			$.each( msg, function( key, value ) {
				var trBody = '<tr onmouseover="changeColor(this);" onmouseout="cancleColor(this);" ondblclick="viewIssue('+ value.id +')" bakClass="#F5F3E5">' + 
					'<td>'+ (key + 1) +'</td>' + 
					'<td>'+ value.issueType +'</td>' + 
					'<td><div class="ellipsisSummary">'+ value.summary.toString().replace("<","&lt;").replace(">","&gt;") +'</div></td>' + 
					'<td><div class="ellipsisDescriptionLogs">'+ value.descriptionLogs.toString().replace("<","&lt;").replace(">","&gt;") +'</div></td>' +  
					'<td><div class="ellipsisSolution">'+ value.solution +'</div></td>' + 
					'<td>'+ value.fixedBy +'</td>' + 
					'<td>'+ value.inputDate +'</td>' + 
					'<td>'+ value.inputName +'</td>' + 
					'<td><div class="ui-state-default ui-corner-all icon"><a href="javascript:void(0)" class="ui-icon ui-icon-comment" title="view" onclick="viewIssue('+ value.id +')"></a></div>';
				var welcomeUser = $("#welcomeUser").html();
				var userRole = $("#userRole").val();
				if(welcomeUser == value.inputName || "1" == userRole) {
					trBody += '<div class="ui-state-default ui-corner-all icon pencilButton"><a href="javascript:void(0)" class="ui-icon ui-icon-pencil" title="upd" onclick="updIssueDialog('+ value.id +')"></a></div>' +
					'<div class="ui-state-default ui-corner-all icon trashButton"><a href="javascript:void(0)" class="ui-icon ui-icon-trash" title="del" onclick="delIssueDialog('+ value.id +')"></a></div>';
				} else {
					trBody += '<div class="ui-widget-content ui-corner-all icon pencilButton"><div class="ui-icon ui-icon-pencil" ></div></div>' +
					'<div class="ui-widget-content ui-corner-all icon trashButton"><div class="ui-icon ui-icon-trash"></div></div>';
				}
				trBody += '</td></tr>';
				$("#list").find("table").find("tbody").append(trBody);
			});
			$('#myTable').dataTable({
				"bProcessing" : true,
				"iDisplayLength":10
			});
			initIssueDialog();
			$(".exportButton").hide();
		});
	}
	function initIssueDialog() {
		dialogAdd = $("#dialog-form").dialog({
		      autoOpen: false,
		      height: 700,
		      width: 550,
		      modal: true,
		      buttons: {
	    	  	"Create a issue": addIssue,
		        Cancel: function() {
		          formAdd[0].reset();
		          dialogAdd.dialog( "close" );
		        }
		      },
		      close: function() {
		    	  formAdd[0].reset();
		      }
		    });
	    formAdd = dialogAdd.find( "form#issue" ).on( "submit", function( event ) {
	      event.preventDefault();
	      return false;
	    });
	    $( "#create-issue" ).on( "click", function() {
	    	dialogAdd.dialog( "open" );
	    	formAdd[0].reset();
	    });
	}
	function initDeliverDialog() {
		dialogDeliverAdd = $("#dialog-form-deliver").dialog({
		      autoOpen: false,
		      width: 750,
		      modal: true,
		      buttons: {
	    	  	"Create a Deliver": addDeliver,
		        Cancel: function() {
		          formDeliverAdd[0].reset();
		          dialogDeliverAdd.dialog( "close" );
		        }
		      },
		      open: function() {
		          formDeliverAdd[0].reset();
		      },
		      close: function() {
		    	  formDeliverAdd[0].reset();
		      }
		    });
		 
	    formDeliverAdd = dialogDeliverAdd.find( "form#deliver" ).on( "submit", function( event ) {
	      event.preventDefault();
	      return false;
	    });
	 
	    $( "#create-deliver" ).on( "click", function() {
	    	dialogDeliverAdd.dialog( "open" );
	    	$(".selectmenu").selectmenu();
	    	$( "#deliveryDate" ).datepicker();
	    	$( "#deliveryDate" ).val(getFormattedDate(new Date()));
	    	addDeliverInit();
	    	$( ".radioset" ).buttonset();
	    });
	}
	function getFormattedDate(date) {
	  var year = date.getFullYear();
	  var month = (1 + date.getMonth()).toString();
	  month = month.length > 1 ? month : '0' + month;
	  var day = date.getDate().toString();
	  day = day.length > 1 ? day : '0' + day;
	  return month + '/' + day + '/' + year;
	}
	function changeColor(obj) {
		$(obj).attr("class","trOverColor");
	}
	function cancleColor(obj) {
		$(obj).attr("class",$(obj).attr("bakClass"));
	}
	function getList() {
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/issues/getList",
			dataType : "json"
		}).done(function(msg) {
			$('#myTable').find("thead").html("");
			$('#myTable').find("tbody").html("");
			$('#myTable').dataTable().fnClearTable();
			$('#myTable').dataTable().fnDestroy();
			var issueTr = '<tr>' + 
			'<td width="2%" class="titleBold">No</td>' + 
			'<td width="10%" class="titleBold">IssueType</td>' + 
			'<td width="10%" class="titleBold">Summary</td>' + 
			'<td width="30%" class="titleBold">Description logs</td>' + 
			'<td width="10%" class="titleBold">Solution</td>' + 
			'<td width="10%" class="titleBold">Fixed By</td>' + 
			'<td width="10%" class="titleBold">Input Date</td>' + 
			'<td width="10%" class="titleBold">Add By</td>' + 
			'<td width="8%" class="titleBold"><span class="operation">Oper</span>' + 
			'<div class="ui-state-default ui-corner-all iconAdd"><a id="create-issue" class="ui-icon ui-icon-plusthick" title="add" href="javascript:void(0);"></a></div>' + 
			'</td></tr>';
		$("#list").find("table").find("thead").append(issueTr);
			$.each( msg, function( key, value ) {
			  var trBody = '<tr onmouseover="changeColor(this);" onmouseout="cancleColor(this);" ondblclick="viewIssue('+ value.id +')" bakClass="#F5F3E5">' + 
				'<td>'+ (key + 1) +'</td>' + 
				'<td>'+ value.issueType +'</td>' + 
				'<td><div class="ellipsisSummary">'+ value.summary.toString().replace("<","&lt;").replace(">","&gt;") +'</div></td>' + 
				'<td><div class="ellipsisDescriptionLogs">'+ value.descriptionLogs +'</div></td>' +  
				'<td><div class="ellipsisSolution">'+ value.solution +'</div></td>' + 
				'<td>'+ value.fixedBy +'</td>' + 
				'<td>'+ value.inputDate +'</td>' + 
				'<td>'+ value.inputName +'</td>' + 
				'<td><div class="ui-state-default ui-corner-all icon"><a href="javascript:void(0)" class="ui-icon ui-icon-comment" title="view" onclick="viewIssue('+ value.id +')"></a></div>';
			  var welcomeUser = $("#welcomeUser").html();
			  var userRole = $("#userRole").val();
				if("1" == userRole || welcomeUser == value.inputName) {
					trBody += '<div class="ui-state-default ui-corner-all icon pencilButton"><a href="javascript:void(0)" class="ui-icon ui-icon-pencil" title="upd" onclick="updIssueDialog('+ value.id +')"></a></div>' +
					'<div class="ui-state-default ui-corner-all icon trashButton"><a href="javascript:void(0)" class="ui-icon ui-icon-trash" title="del" onclick="delIssueDialog('+ value.id +')"></a></div>';
				} else {
					trBody += '<div class="ui-widget-content ui-corner-all icon pencilButton"><div class="ui-icon ui-icon-pencil" ></div></div>' +
					'<div class="ui-widget-content ui-corner-all icon trashButton"><div class="ui-icon ui-icon-trash"></div></div>';
				}
				trBody += '</td></tr>';
				$("#list").find("table").find("tbody").append(trBody);
			});
			$('#myTable').dataTable({
				"bProcessing" : true,
				"iDisplayLength":10
			});
			initIssueDialog();
			$(".exportButton").hide();
		});	
	}

	function getDeliverList() {
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/deliver/getList",
			dataType : "json"
		}).done(function(msg) {
			$('#myTable').find("thead").html("");
			$('#myTable').find("tbody").html("");
			$('#myTable').dataTable().fnClearTable();
			$('#myTable').dataTable().fnDestroy();
			var tr1 = '<tr>' + 
				'<td width="5%" class="titleBold">No</td>' + 
				'<td width="5%" class="titleBold">Developer</td>' + 
				'<td width="10%" class="titleBold">Delivery Date</td>' + 
				'<td width="15%" class="titleBold">Task Summary</td>' + 
				'<td width="15%" class="titleBold">Description</td>' + 
				'<td width="5%" class="titleBold">App Changes</td>' + 
				'<td width="5%" class="titleBold">Web Changes</td>' + 
				'<td width="5%" class="titleBold">Config Changes</td>' + 
				'<td width="5%" class="titleBold">DB Changes</td>' + 
				'<td width="10%" class="titleBold">Status</td>' + 
				'<td width="12%" class="titleBold">Remark</td>' + 
				'<td width="8%" class="titleBold"><span class="operation">Oper</span>' + 
				'<div class="ui-state-default ui-corner-all iconAdd"><a id="create-deliver" class="ui-icon ui-icon-plusthick" title="add" href="javascript:void(0);"></a></div>' + 
				'</td></tr>';
			$("#list").find("table").find("thead").append(tr1);
			$.each( msg, function( key, value ) {
			  var trBody = '<tr onmouseover="changeColor(this);" onmouseout="cancleColor(this);" ondblclick="viewDeliver('+ value.id +')" bakClass="#F5F3E5">' + 
				'<td>'+ (key + 1) +'</td>' + 
				'<td>'+ value.developer +'</td>' + 
				'<td>'+ value.deliveryDate +'</td>' + 
				'<td><div class="ellipsisSummary"><a href="javascript:void(0);">'+ value.taskSummary +'</div></td>' + 
				'<td><div class="ellipsisSummary"><a href="javascript:void(0);">'+ value.description +'</div></td>' + 
				'<td>'+ value.appChanges +'</td>' + 
				'<td>'+ value.webChanges +'</td>' + 
				'<td>'+ value.configChanges +'</td>' + 
				'<td>'+ value.dbChanges +'</td>' + 
				'<td>'+ value.status +'</td>' + 
				'<td><div class="ellipsisSummary">'+ value.remark +'</div></td>' + 
				'<td><div class="ui-state-default ui-corner-all icon"><a href="javascript:void(0)" class="ui-icon ui-icon-comment" title="view" onclick="viewDeliver('+ value.id +')"></a></div>';
			  var welcomeUser = $("#welcomeUser").html();
				var userRole = $("#userRole").val();
				if("1" == userRole || welcomeUser == value.developer) {
					trBody += '<div class="ui-state-default ui-corner-all icon pencilButton"><a href="javascript:void(0)" class="ui-icon ui-icon-pencil" title="upd" onclick="updDeliverDialog('+ value.id +')"></a></div>' +
					'<div class="ui-state-default ui-corner-all icon trashButton"><a href="javascript:void(0)" class="ui-icon ui-icon-trash" title="del" onclick="delDeliverDialog('+ value.id +')"></a></div>';
					$(".exportButton").show();
				} else {
					trBody += '<div class="ui-widget-content ui-corner-all icon pencilButton"><div class="ui-icon ui-icon-pencil" ></div></div>' +
					'<div class="ui-widget-content ui-corner-all icon trashButton"><div class="ui-icon ui-icon-trash"></div></div>';
					$(".exportButton").hide();
				}
				trBody += '</td></tr>';
				$("#list").find("table").find("tbody").append(trBody);
			});
			$('#myTable').dataTable({
				"bProcessing" : true,
				"iDisplayLength":10
			});
			initTooltip();
			initDeliverDialog();
		});	
	}
	
	function addIssue() {
		var param = $("form#issue").serialize();
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/issues/addIssue",
			data : param
		}).done(function(msg) {
			dialogAdd.dialog( "close" );
			getList();
		});
	}
	function addDeliverInit() {
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/deliver/addDeliverInitUser"
		}).done(function(data) {
			$("#developer").val(eval(data));
		});
	}
	function addDeliver() {
		var param = $("form#deliver").serialize();
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/deliver/addDeliver",
			data : param
		}).done(function(msg) {
			dialogDeliverAdd.dialog( "close" );
			getDeliverList();
		});
	}
	function delIssue(delIssueId) {
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/issues/delIssue",
			data : {id : delIssueId}
		}).done(function(msg) {
			dialogIssueDel.dialog( "close" );
			getList();
		});
	}
	function delDeliver(delDeliverId) {
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/deliver/delDeliver",
			data : {id : delDeliverId}
		}).done(function(msg) {
			dialogDeliverDel.dialog( "close" );
			getDeliverList();
		});
	}
	function delIssueDialog(id) {
		$( "#dialog-del-issue" ).click();
		$("#delIssueId").val(id);
	}
	function delDeliverDialog(id) {
		$( "#dialog-del-deliver" ).click();
		$("#delDeliverId").val(id);
	}
	function viewIssue(id) {
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/issues/getIssue",
			data : {"id" : id},
			dataType : "json"
		}).done(function(data) {
			$("#issueTypeDialog").html(data.issueType);
			$("#summaryDialog").text(data.summary);
			$("#descriptionLogsDialog").text(data.descriptionLogs);
			$("#solutionDialog").html(data.solution);
			$("#fixedByDialog").html(data.fixedBy);
			$("#addByDialog").html(data.inputName);
			$( "#dialog" ).dialog({
				autoOpen: false,
				width: 860,
				modal: true,
				buttons: [
					{
						text: "Ok",
						click: function() {
							$( this ).dialog( "close" );
						}
					},
					{
						text: "Cancel",
						click: function() {
							$( this ).dialog( "close" );
						}
					}
				]
			});
			$( "#dialog" ).dialog( "open" );
		});
	}
	function updIssueDialog(id) {
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/issues/updInitIssue",
			data : {"id" : id},
			dataType : "json"
		}).done(function(data) {
			$("#issueUpdId").val(data.id);
			$("#issueTypeUpd").val(data.issueType);
			$("#summaryUpd").val(data.summary);
			$("#descriptionLogsUpd").val(data.descriptionLogs);
			$("#solutionUpd").val(data.solution);
			$("#fixedByUpd").val(data.fixedBy);
			$( "#dialog-form-upd" ).dialog({
				autoOpen: false,
				width: 750,
				modal: true,
				buttons: [
					{
						text: "Update",
						click: function() {
							updIssue();
						}
					},
					{
						text: "Cancel",
						click: function() {
							$( this ).dialog( "close" );
						}
					}
				]
			});
			$( "#dialog-form-upd" ).dialog( "open" );
			$( "#dialog-form-upd" ).on("submit",function(event) {
				event.preventDefault();
				return false;
			});
		});
	}
	function updIssue() {
		var param = $("form#issueUpd").serialize();
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/issues/updIssue",
			data : param
		}).done(function(data) {
			$( "#dialog-form-upd" ).dialog( "close" );
			getList();
		}).fail(function(jqXHR, textStatus ) {
		    alert( "Request failed: " + textStatus );
		  });
	}
	function viewDeliver(id) {
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/deliver/getDeliver",
			data : {"id" : id},
			dataType : "json"
		}).done(function(data) {
			$("#developerDialog").html(data.developer);
			$("#deliveryDateDialog").html(data.deliveryDate);
			$("#taskSummaryDialog").html(data.taskSummary);
			$("#descriptionDialog").html(data.description);
			$("#appChangesDialog").html(data.appChanges);
			$("#webChangesDialog").html(data.webChanges);
			$("#configChangesDialog").html(data.configChanges);
			$("#dbChangesDialog").html(data.dbChanges);
			$("#statusDialog").html(data.status);
			$("#regionDialog").html(data.region);
			$("#uxfdpDialog").html(data.uxfdp);
			$("#uxfvfDialog").html(data.uxfvf);
			$("#gcBuildIqadpDialog").html(data.gcBuildIqadp);
			$("#gcBuildIqavfDialog").html(data.gcBuildIqavf);
			$("#gcUatIqadpDialog").html(data.gcUatIqadp);
			$("#gcUatIqavfDialog").html(data.gcUatIqavf);
			$("#sitdpDialog").html(data.sitdp);
			$("#sitvfDialog").html(data.sitvf);
			$("#uatdpDialog").html(data.uatdp);
			$("#uatvfDialog").html(data.uatvf);
			$("#remarkDialog").html(data.remark);
			$( "#dialogDeliver" ).dialog({
				autoOpen: false,
				width: 860,
			    modal: true,
				buttons: [
					{
						text: "Ok",
						click: function() {
							$( this ).dialog( "close" );
						}
					},
					{
						text: "Cancel",
						click: function() {
							$( this ).dialog( "close" );
						}
					}
				]
			});
			$( "#dialogDeliver" ).dialog( "open" );
		});
	}
	function updDeliver() {
		var param = $("form#deliverUpd").serialize();
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/deliver/updDeliver",
			data : param,
			dataType : "json"
		}).done(function(data) {
			$( "#dialog-form-deliver-upd" ).dialog( "close" );
			getDeliverList();
		});
	}
	function updDeliverDialog(id) {
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/deliver/updInitDeliver",
			data : {"id" : id},
			dataType : "json"
		}).done(function(data) {
			$("#idUpd").val(data.id);
			$("#developerUpd").val(data.developer);
			$("#deliveryDateUpd").val(data.deliveryDate);
			$("#taskSummaryUpd").val(data.taskSummary);
			$("#descriptionUpd").val(data.description);
			if(data.appChanges == 'Y') {
				$("#appChangesUpd2").attr("checked","checked");
			} else {
				$("#appChangesUpd1").attr("checked","checked");
			}
			if(data.webChanges == 'Y') {
				$("#webChangesUpd2").attr("checked","checked");
			} else {
				$("#webChangesUpd1").attr("checked","checked");
			}
			if(data.configChanges == 'Y') {
				$("#configChangesUpd2").attr("checked","checked");
			} else {
				$("#configChangesUpd1").attr("checked","checked");
			}
			if(data.dbChanges == 'Y') {
				$("#dbChangesUpd2").attr("checked","checked");
			} else {
				$("#dbChangesUpd1").attr("checked","checked");
			}
			$("#statusUpd").val(data.status);
			$("#regionUpd").val(data.region);
			$("#uxfdpUpd").val(data.uxfdp);
			$("#uxfvfUpd").val(data.uxfvf);
			$("#gcBuildIqadpUpd").val(data.gcBuildIqadp);
			$("#gcBuildIqavfUpd").val(data.gcBuildIqavf);
			$("#gcUatIqadpUpd").val(data.gcUatIqadp);
			$("#gcUatIqavfUpd").val(data.gcUatIqavf);
			$("#sitdpUpd").val(data.sitdp);
			$("#sitvfUpd").val(data.sitvf);
			$("#uatdpUpd").val(data.uatdp);
			$("#uatvfUpd").val(data.uatvf);
			$("#remarkUpd").val(data.remark);
			$( "#dialog-form-deliver-upd" ).dialog({
				autoOpen: false,
				width: 750,
				modal: true,
				buttons: [
					{
						text: "Update",
						click: function() {
							updDeliver();
						}
					},
					{
						text: "Cancel",
						click: function() {
							$( this ).dialog( "close" );
						}
					}
				]
			});
			$( "#dialog-form-deliver-upd" ).dialog( "open" );
	    	$(".selectmenu").selectmenu();
	    	$( "#deliveryDateUpd" ).datepicker();
			$( "#dialog-form-deliver-upd" ).on("submit",function(event) {
				event.preventDefault();
				return false;
			});
		});
	}
	function exportToExcel() {
		var labelName = $("#labelName").val();
		var searchParam = $("input[type='search']").val();
		$.ajax({
			type : "get",
			url : "/knowledgeSharing/deliver/getListForExportToExcel",
			data : {"strParam" : searchParam},
			dataType : "json"
		}).done(function(data) {
			window.location.href = "/knowledgeSharing/download/forExportToExcel?fileName=" + data;
		});
	}