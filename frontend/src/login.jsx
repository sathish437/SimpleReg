import { useState } from "react"
import { login } from "./services/authService"
import { useNavigate } from "react-router-dom"

function Login(){
    const navigate = useNavigate()
    let [userName,setUserName]=useState("")
    let [password,setPassword]=useState("")
    let [loading,setLoading]=useState(false)

    const handleLogin = async () => {
        if(!userName || !password){
            alert("Please enter username and password")
            return
        }

        setLoading(true)
        
        try {
            const loginData = {
                userName: userName,
                password: password
            }
            
            const response = await login(loginData)
            
            alert(`Login successful! Welcome ${response.userName}`)
            navigate("/welcome")
        } catch (error) {
            alert(`Login failed: ${error.message}`)
        } finally {
            setLoading(false)
        }
    }

    return(
        <>
            <main className='flex min-h-screen w-full bg-blue-600 justify-center items-center p-4 sm:p-6 lg:p-8'>
            <section className='rounded-3xl p-4 sm:p-6 lg:p-8 bg-white w-full max-w-[400px] shadow-2xl'>
                <h1 className='flex text-xl sm:text-2xl font-bold justify-center'><p className='underline'>Lo</p>gin</h1>
                <div className='flex flex-col gap-3 mt-6 sm:mt-10'>
                    <input name="userName" onChange={(e)=>setUserName(e.target.value)} className='p-3 border-b-2 rounded-[5px] border-gray-500 w-full focus:outline-none focus:border-blue-600 transition-colors' type='text' placeholder="Enter your user name" />
                    <input name="password" onChange={(e)=>setPassword(e.target.value)} className='p-3 border-b-2 rounded-[5px] border-gray-500 w-full focus:outline-none focus:border-blue-600 transition-colors' type="password" placeholder="Enter your password" />
                </div>
                <nav className='flex justify-between items-center mt-4 gap-2 flex-wrap'>
                    <div className='flex items-center gap-2'>
                        <input id="remember" className='w-4 h-4 cursor-pointer' type='checkbox'/>
                        <label htmlFor="remember" className='text-gray-600 text-sm sm:text-base cursor-pointer select-none'>Remember me</label>
                    </div>
                    <a href="" className="text-blue-600 font-semibold text-sm sm:text-base hover:underline">Forgot password?</a>
                </nav>
                <button onClick={handleLogin} disabled={loading} className={`w-full active:scale-95 hover:bg-blue-700 transition-all mt-5 p-3 rounded-2xl text-white font-medium ${loading ? 'bg-gray-400 cursor-not-allowed' : 'bg-blue-600'}`}>
                    {loading ? 'Logging in...' : 'Login Now'}
                </button>
                <p className='flex justify-center mt-5 text-sm sm:text-base flex-wrap gap-1'>Don't have an account?<a className='text-blue-600 font-semibold hover:underline' href="/reg">Signup now</a></p>
            </section>
            </main>
        </>
    )
}
export default Login