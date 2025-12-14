import React from 'react'

function Footer({isLogin}) {
  return (
    <div className='footer-section'>
        

        {
            !isLogin ? (
                <>
                    <hr className='m-2 mt-10' />
                    <div className='flex flex-col justify-between gap-2 items-center'>
                        <h1 className='font-semibold text-2xl'>See personalized recommendations</h1>
                        <a
                        href='/Auth'
                        className='font-semibold bg-white hover:bg-gray-300 text-center cursor-pointer text-black p-2 rounded-xl w-sm'
                        >Sign in</a>
                        <p>New customer? <a className='text-blue-400' href=""> Start here.</a></p>
                    </div>
                </>
                
            )
            :
            (
                ""
            )
        }
        

        <hr className='m-2 mt-10' />

        <div className='flex bg-gray-800 p-10 justify-center items-center gap-20'>

            <div className='us-know flex flex-col justify-start'>
                    <h1 className='font-bold text-xl'>Get to know Us</h1>
                    <a href="/OneEcommerce" className='hover:underline'>About OneEcommerce</a>
                    <a href="" className='hover:underline'>Careers</a>
                    <a href="" className='hover:underline'>Press Release</a>
                    <a href="" className='hover:underline'>OneEcommerce Choice</a>
            </div>

            <div className='flex  flex-col justify-start'>
                <h1 className='font-bold text-xl'>Connect With Us</h1>
                <a href="" className='hover:underline'>Facebook</a>
                <a href="" className='hover:underline'>Twitter</a>
                <a href="" className='hover:underline'>Instagram</a>
                <a href="" className='hover:underline'>Youtube</a>
            </div>

            <div className='make-money flex flex-col justify-start'>
                <h1 className='font-bold text-xl'>Make Money With Us</h1>
                <a href="" className='hover:underline'>Sell on OneEcommerce</a>
                <a href="" className='hover:underline'>Supply to Amazon</a>
                <a href="" className='hover:underline'>Become Affiliates</a>
                <a href="" className='hover:underline'>OneEcommerce Pay on Merchants</a>
            </div>

            <div className='let-us flex flex-col justify-start'>
                <h1 className='font-bold text-xl'>Let Us With You</h1>
                <a href="" className='hover:underline'>Your Account</a>
                <a href="" className='hover:underline'>Return Centre</a>
                <a href="" className='hover:underline'>100% Purchase Protection</a>
                <a href="" className='hover:underline'>Help</a>
            </div>

        </div>

        <div className='branding flex flex-col justify-center text-sm items-center gap-2 p-2  '>
            <div className='sub-task flex'>
                <a href="" className='hover:underline'>Conditions of Use & Sale</a>
                <a href="" className='hover:underline'>Privacy Notice</a>
                <a href="" className='hover:underline'>Interest-Based Ad</a>
            </div>

            <div className='another-task'>
                <p>© 2025 - Present, OneEcommerce.com</p>
            </div>
        </div>
    </div>
  )
}

export default Footer