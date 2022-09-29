class AppPage {
    static renderFooter(obj) {
        return `
            <div class="footer">
                <div>
                    <button class="btn btn-outline-primary edit" data-id="${obj.id}"
                        data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Edit">
                        <i class="fas fa-edit"></i>
                    </button>
                </div>
                <div>
                    <button class="btn btn-outline-success deposit" data-id="${obj.id}"
                        data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Deposit">
                        <i class="fas fa-plus"></i>
                    </button>
                </div>
                <div>
                    <button class="btn btn-outline-warning withdraw" data-id="${obj.id}"
                        data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Withdraw">
                        <i class="fas fa-minus"></i>
                    </button>
                </div>
                <div>
                    <button class="btn btn-outline-primary transfer" data-id="${obj.id}"
                        data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Transfer">
                        <i class="fas fa-exchange-alt"></i>
                    </button>
                </div>
                <div>
                    <button class="btn btn-outline-danger delete" data-id="${obj.id}"
                        data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Delete">
                        <i class="far fa-trash-alt"></i>
                    </button>
                </div>
            </div>
        `;
    }

    static renderProductItem(obj) {
        return `
            <div class="content mr2 fl">
                <div class="card" style="width: 18rem;">
                    <img src="/assets/img/${obj.avatar}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${obj.name}</h5>
                        <p class="card-text">${obj.price} vnđ</p>
                        <a href="#" class="btn btn-primary add-cart" data-id="${obj.id}">Add to cart</a>
                    </div>
                </div>
            </div>
        `;
    }
}