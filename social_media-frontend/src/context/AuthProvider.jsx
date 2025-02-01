import { useState } from "react"
import { AuthContext } from "./AuthContext"

const AuthProvider = ({children}) => {

  const [isAuthenticated, setIsAuthenticated] = useState(false)
  const [user, setUser] = useState(null)

  const login = (userData) => {
    setIsAuthenticated(true)
    setUser(userData)
    console.log("login success")
    console.log(user)
  }
  const logout = () => {
    setUser(null)
    console.log("logout", user)
    setIsAuthenticated(false)
  }

  return (
    <AuthContext.Provider value={{isAuthenticated, user, login, logout}}>
      {children}
    </AuthContext.Provider>
  )
}

export default AuthProvider