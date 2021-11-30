

export default function GoodsList(props) {

    let data = [
        {
            title: '潘婷氨基酸乳液修洗护套装',
            disc: '限量抢购300件',
            price: '9.9',
            pic: './upload/goods-img-1.jpg'
        },
        {
            title: '潘婷氨基酸乳液修洗护套装',
            disc: '限量抢购300件',
            price: '9.9',
            pic: './upload/goods-img-1.jpg'
        },
        {
            title: '潘婷氨基酸乳液修洗护套装',
            disc: '限量抢购300件',
            price: '9.9',
            pic: './upload/goods-img-1.jpg'
        },
        {
            title: '潘婷氨基酸乳液修洗护套装',
            disc: '限量抢购300件',
            price: '9.9',
            pic: './upload/goods-img-1.jpg'
        },
        {
            title: '潘婷氨基酸乳液修洗护套装',
            disc: '限量抢购300件',
            price: '9.9',
            pic: './upload/goods-img-1.jpg'
        },
        {
            title: '潘婷氨基酸乳液修洗护套装',
            disc: '限量抢购300件',
            price: '9.9',
            pic: './upload/goods-img-1.jpg'
        },
        {
            title: '潘婷氨基酸乳液修洗护套装',
            disc: '限量抢购300件',
            price: '9.9',
            pic: './upload/goods-img-1.jpg'
        },
    ]

    return (
        <ul className='goods-list'>
            {
                data.map((value, index) => {
                    return (
                        <li className='goods-item' key={index}>
                            <div className='goods-main'>
                                <img src={value.pic} alt=''></img>
                                <h4 className='goods-title'>{value.title}</h4>
                                <p className='goods-disc'>{value.disc}</p>
                            </div>
                            <div className='goods-info'>
                                <div className='price'>
                                    <em>￥</em>
                                    {value.price}
                                </div>
                                <div className='buy-btn'>
                                    立即抢购
                                </div>
                            </div>
                        </li>
                    );
                })
            }

        </ul>
    );
}