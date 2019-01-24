public class Bookflight extends WebDriverBaseTestPage<WebDriverTestPage>{
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.bookingdetails.tbl")
	private QAFWebElement bookingdetails;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.summary.lbl")
	private QAFWebElement summary;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.departroot.lbl")
	private QAFWebElement departroot;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.departdate.lbl")
	private QAFWebElement departdate;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.departairline.lbl")
	private QAFWebElement departairline;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.departclass.lbl")
	private QAFWebElement departclass;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.departprice.lbl")
	private QAFWebElement departprice;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.returnroot.lbl")
	private QAFWebElement returnroot;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.returndate.lbl")
	private QAFWebElement returndate;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.returnairline.lbl")
	private QAFWebElement returnairline;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.returnclass.lbl")
	private QAFWebElement returnclass;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.returnprice.lbl")
	private QAFWebElement returnprice;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.passcount.lbl")
	private QAFWebElement passcount;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.taxes.lbl")
	private QAFWebElement taxes;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.totalprice.lbl")
	private QAFWebElement totalprice;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.firstname.txt")
	private QAFWebElement firstname;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.lastname.txt")
	private QAFWebElement lastname;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.purchase.btn")
	private QAFWebElement purchase;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.billAddress1.txt")
	private QAFWebElement billAddress1;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.billAddress2.txt")
	private QAFWebElement billAddress2;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.billCity.txt")
	private QAFWebElement billCity;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.billState.txt")
	private QAFWebElement billState;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.billZip.txt")
	private QAFWebElement billZip;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.billCountry.txt")
	private QAFWebElement billCountry;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.delAddress1.txt")
	private QAFWebElement delAddress1;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.delAddress2.txt")
	private QAFWebElement delAddress2;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.delCity.txt")
	private QAFWebElement delCity;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.delState.txt")
	private QAFWebElement delState;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.delZip.txt")
	private QAFWebElement delZip;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.delCountry.txt")
	private QAFWebElement delCountry;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.creditCard.txt")
	private QAFWebElement creditCard;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.creditnumber.txt")
	private QAFWebElement creditnumber;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.cc_exp_dt_mn.txt")
	private QAFWebElement cc_exp_dt_mn;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.cc_exp_dt_yr.txt")
	private QAFWebElement cc_exp_dt_yr;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.cc_frst_name.txt")
	private QAFWebElement cc_frst_name;
	@Randomizer(length=0)
	@FindBy(locator = "bookflight.cc_mid_name.txt")
	private QAFWebElement cc_mid_name;
}