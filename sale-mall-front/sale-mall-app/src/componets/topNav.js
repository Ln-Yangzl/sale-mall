

export default function TopNav(props) {

    return (
        <header>
            <div className="top-nav">
                <div className="width-restrictor top-nav-flex">
                    <ul className="top-left">
                        <li>请登录</li>
                        <li>免费注册</li>
                    </ul>
                    <ul className="top-right">
                        <li>我的订单</li>
                        <li>客户服务</li>
                    </ul>
                </div>
            </div>
            <div className="mid-header">
                <div className="width-restrictor mid-header-flex">
                    <img className='logo' src='./images/top-logo.jpg' alt=''>
                    </img>
                    <div className='right-band'>
                        我的购物车
                    </div>
                </div>
            </div>
            <div className="bottom-nav">
                <div className="width-restrictor">
                    <ul className="nav-list">
                        <li className="on">
                            特价秒杀
                        </li>
                        <li>
                            品牌秒杀
                        </li>
                        <li>
                            品牌秒杀
                        </li>
                    </ul>
                </div>
            </div>
        </header>
    );
}