import React, { useState } from 'react'
import Navbar from './Navbar'
import axios from 'axios';
import toast, {Toaster} from 'react-hot-toast'

function Auth({handleLocation, Location}) {

    const [username, setUsername] = useState("")
    const [password, setpassword] = useState("")
    const [isSignInActive, setIsSignupActive] = useState(false);



    const handleSubmit = async(e) =>{
        e.preventDefault();
        console.log(username, password);
        
        try{
            const res = await axios.post("http://localhost:8080/api/users/login",
                {username, password},
                {withCredentials : true,
                    headers: {
                        "Content-Type": "application/json"
                    },
                }
            )
            
            toast.success('Logged in successfully!')
            console.log(res.data);
        }
        catch(error)
        {
            console.error(error);
            toast.error("Incorrect Credentials");
        }

        setUsername("");
        setpassword("");
    }


  return (
    <div className='Auth-container'>
        <Navbar handleLocation={handleLocation} Location={Location} />
        <div className='login-container flex mt-20 justify-center'>
            <form className='flex p-2 flex-col border gap-2 '>


            {
                !isSignInActive && (
                    <input 
                        type="text" 
                        placeholder='Enter username'
                        value={username}
                        className='p-2 border w-xs font-semibold'
                        required
                        onChange={(e) => setUsername(e.target.value)}
                    />
                )
            }


            
            {
                !isSignInActive && (
                    <input 
                        type="password" 
                        placeholder='Enter password' 
                        value={password}
                        className='p-2 border w-xs font-semibold'
                        required
                        onChange={(e) => setpassword(e.target.value)}
                    />
                )
            }
               
               

                <div className='flex  justify-end'>
                    <a href="">Forget Password!</a>
                </div>
               
                <button 
                    className='bg-white text-black font-semibold cursor-pointer p-2 hover:border'
                    onClick={handleSubmit}
                    type='submit'
                >Submit</button>
                <Toaster
                    position="top-center"
                    reverseOrder={false}
                />

                <div className='flex justify-center'>

                    {
                        !isSignInActive ? 
  
                            <p className='text-gray-500'>Don't have an account:
                            <button 
                                href="/register" 
                                className='text-blue-700 hover:underline'
                                onClick={()=> setIsSignupActive(false)}
                            >Sign Up</button></p>
        
                         :
                            <p className='text-gray-500'> you have an account:
                            <button 
                            href="/register" 
                            className='text-blue-700 hover:underline'
                            onClick={()=> setIsSignupActive(true)}
                            >Sign in</button></p>
                    }
                     
                </div>
            </form>
            

        </div>
    </div>
  )
}

export default Auth