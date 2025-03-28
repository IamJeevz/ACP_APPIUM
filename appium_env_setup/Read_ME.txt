** How to SetUp Appium

1) Run andoid_Tools.bat
	This will install Android Tools like cmdline tools and Platform tools
	(This tools are necessary to link the device with pc inorder to run the Appium)		
2) Run appium_setup.bat
	This will install Node.js first since Node is required to run Appium on PC
	The current version of Node will be overwritten with a newer version
	(NB: Stop all node process before running this)
	Then Appium will be installed after that.
3) Run UI_auto.bat
	This will install UI_automator2 for appium
	(The UIAutomator2 framework provides additional advanced capabilities, like interacting with system UI elements)