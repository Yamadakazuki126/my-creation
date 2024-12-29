document.addEventListener('DOMContentLoaded', () => {
    const buyButtons = document.querySelectorAll('.buy');
    const modal = document.getElementById('modal');
    //const confirmBuy = document.getElementById('confirm-buy');
    const buyForm = document.getElementById('buy-form');
    let selectedItemName = ''; // 商品名を格納する変数

    // BUYボタンがクリックされたとき
    buyButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault(); // デフォルトの動作を防止
            const form = button.closest('form'); // クリックされたボタンの親フォームを取得
            selectedItemName = form.querySelector('input[type="submit"]').name; // name属性を取得
            // モーダルを表示
            modal.style.display = 'flex'; 

            // 商品情報をフォームにセット
            buyForm.querySelector('button').setAttribute('value', selectedItemName); 
        });
    });

    // モーダル外をクリックしたときに閉じる
    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });
});
