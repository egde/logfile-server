angular
		.module("services")
		.factory(
				"logService",
				function() {

					return {
						retrieve : function(hash) {
							var dummy = ""
									+ "09.03.17, 19:46:54 MEZ: [INFO] Copying 0 resource"
									+ "09.03.17, 19:46:54 MEZ: [INFO] Copying 0 resource"
									+ "09.03.17, 19:46:54 MEZ: [INFO] Using 'UTF-8' encoding to copy filtered resources."
									+ "09.03.17, 19:46:54 MEZ: [INFO] Copying 0 resource"
									+ "09.03.17, 19:47:20 MEZ: [INFO] Using 'UTF-8' encoding to copy filtered resources."
									+ "09.03.17, 19:47:20 MEZ: [INFO] Copying 0 resource"
									+ "09.03.17, 19:47:20 MEZ: [INFO] Copying 0 resource"
									+ "09.03.17, 19:47:20 MEZ: [INFO] Using 'UTF-8' encoding to copy filtered resources."
									+ "09.03.17, 19:47:20 MEZ: [INFO] Copying 0 resource"
									+ "09.03.17, 19:47:39 MEZ: [INFO] Using 'UTF-8' encoding to copy filtered resources."
									+ "09.03.17, 19:47:39 MEZ: [INFO] Copying 0 resource"
									+ "09.03.17, 19:47:39 MEZ: [INFO] Copying 1 resource"
									+ "09.03.17, 19:47:39 MEZ: [INFO] Using 'UTF-8' encoding to copy filtered resources."
									+ "09.03.17, 19:47:39 MEZ: [INFO] Copying 0 resource";
							
							return dummy;
						}
					}
				});