import React from 'react'
import Navbar from './Navbar'

export default function Error(){
  return (
    <div className='error-section flex justify-center items-center h-96 '>
        <div className='flex flex-col  justify-center gap-2 items-center'>
            <h1 className='font-semibold text-5xl'>404😒 Oops!</h1>

            <p className='font-semibold text-2xl text-gray-400'>Please check your internet connection or try again later.</p>
            <p className='font-semibold'> </p>
            <a 
                href='/'
                className='font-semibold cursor-pointer text-black bg-white p-2 hover:border'
            >Retry</a>
        </div>                 
     </div>
  )
}
