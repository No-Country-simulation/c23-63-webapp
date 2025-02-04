import { useEffect, useState } from "react"
import { AuthContext } from "./AuthContext"

const AuthProvider = ({children}) => {

  const [isAuthenticated, setIsAuthenticated] = useState(false)
  const [user, setUser] = useState(null)

  const userDataPMano = {
    "id": 1,
    "email": "user1@example.com",
    "password": "123456"
  }

  const login = (userData) => {
    setIsAuthenticated(true)
    setUser(userData)
    localStorage.setItem('user', JSON.stringify(userData));
    console.log("login success", userData, user)
  }
  const login2 = () => {
    setIsAuthenticated(true)
    setUser(userDataPMano)
    localStorage.setItem('user', JSON.stringify(userDataPMano))
  }

  const logout = () => {
    setIsAuthenticated(false)
    setUser(null)
    localStorage.removeItem('user')
    console.log("logout", user)
  }

  useEffect(()=>{
    const storedUser = localStorage.getItem('user')
    if (storedUser) {
      setUser(JSON.parse(storedUser))
      setIsAuthenticated(true)
    }
  },[])

  return (
    <AuthContext.Provider value={{isAuthenticated, user, login, logout, login2}}>
      {children}
    </AuthContext.Provider>
  )
}

export default AuthProvider