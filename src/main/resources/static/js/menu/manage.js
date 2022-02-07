$(function () {
    let i;
    const tabs = $('.tabs li');

    for (i = 0; i < tabs.length; i++) {
        tabs.eq(i).click(function () {
            activateTab(this.id);
        });
    }

    const tabContents = $('.tab_content');

    activateTab('COFFEE_HOT');	// default tab

    function activateTab(tabId) {
        let i;
        for (i = 0; i < tabs.length; i++) {
            if (tabs.eq(i).attr('id') === tabId) {
                tabs.eq(i).addClass('active');
            } else {
                tabs.eq(i).removeClass('active');
            }
        }

        for (i = 0; i < tabContents.length; i++) {
            if (tabContents.eq(i).attr('id') === 'TAB_' + tabId) {
                tabContents.eq(i).addClass('active');
            } else {
                tabContents.eq(i).removeClass('active');
            }
        }
    }
});