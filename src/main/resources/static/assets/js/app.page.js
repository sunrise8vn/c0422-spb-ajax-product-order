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

    static renderCartItem(obj) {
        return `
            <div id="ci_${obj.id}" class="card mb-3" style="max-width: 100%;">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src="/assets/img/${obj.avatar}" width="150px" class="img-fluid rounded-start" alt="...">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">${obj.productName}</h5>
                            <p class="card-text">
                                <span class="price">${obj.productPrice}</span>
                                <span>
                                    <button class="btn btn-danger minus" data-id="${obj.id}">-</button>
                                </span>
                                <span>
                                    <input type="text" class="form-control quantity" data-id="${obj.id}" value="${obj.quantity}">
                                </span>
                                <span>
                                    <button class="btn btn-success add" data-id="${obj.id}">+</button>
                                </span>
                                <span class="amount">${obj.amount}</span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }
}