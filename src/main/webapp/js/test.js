$("#link_lkRegister").click();
setTimeout(
		function() {
			$("#image_lkLogonIDType1").click();
			setTimeout(
					function() {
						$("#link_lkSignonTCYes").click();
						setTimeout(
								function() {
									$("#cin").val("4265690000111111");
									$("#pin").val("123456");
									$("#acctNumber").val("0459931044");
									setTimeout(
											function() {
												$("#link_lkRegSetupCont_1").click();
												setTimeout(
														function() {
															$("#link_lkSignonTCYes").click();
															setTimeout(
																	function() {
																		$("#username").val("CBOL_PI2");
																		$("#password").val("1qazxsw2");
																		$("#passwordConfirm").val("1qazxsw2");
																		$("#link_lkRegGetCredCont").find("span").click();
																	},20000);
														},3000);
											},1000);
								},3000);
					},3000);
		},3000);