$(function () {
    let i;
    const tabs = $('.tabs li');

    for (i = 0; i < tabs.length; i++) {
        tabs.eq(i).click(function () {
            activateTab(this.id);
        });
    }

    const tabContents = $('.tab_content');

    for (i = 0; i < tabContents.length; i++) {
        const items = tabContents.eq(i).find('.item');

        for (let j = 0; j < items.length; j++) {
            items.eq(j).click(function () {
                order($(this));
            });
        }
    }

    activateTab('COFFEE_HOT');	// default tab

    $('#orderForm').submit(function () {
        const totalPrice = parseInt($('#totalPrice').text());

        if (totalPrice <= 0) {
            alert('메뉴를 선택해주세요.');
            return false;
        }

        $('#price').val(totalPrice);	// to pass the value to OrderServlet
        return true;
    });

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

    function order(menuItem) {
        let tPrice;
        const orderDetails = $('.orderDetails tbody').eq(0);
        const rows = orderDetails.find('tr');

        const menuId = menuItem.find('.menuId').val();
        let targetRow;

        for (let i = 0; i < rows.length; i++) {
            const rowId = rows.eq(i).attr('id');

            if (rowId === menuId) {
                targetRow = rows.eq(i);
                break;
            }
        }

        const totalPrice = $('#totalPrice');
        const price = parseInt(menuItem.find('.price').text());

        if (targetRow) {	// found
            const lastQty = parseInt(targetRow.find('.qty').text());
            targetRow.find('.qty').text(lastQty + 1);

            tPrice = parseInt(totalPrice.text());
            totalPrice.text(tPrice + price);
        } else {
            const menuName = menuItem.find('.menuName').text();

            const template = $('#orderTemplate');
            const row = template.clone();

            row.attr('id', menuId);
            row.find('.menuName').text(menuName);
            row.find('.qty').text('1');
            row.find('.price').text(price);

            const deleteBtn = row.find('button');
            deleteBtn.click(function () {
                const tr = $(this).parents('tr');
                const price = tr.find('.price').text();
                const qty = tr.find('.qty').text();

                totalPrice.text(totalPrice.text() - price * qty);
                tr.remove();
            });

            row.removeClass('blind');
            orderDetails.append(row);

            tPrice = parseInt(totalPrice.text());
            totalPrice.text(tPrice + price);
        }
    }
});