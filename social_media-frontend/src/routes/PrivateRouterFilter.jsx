import { Navigate, Outlet } from "react-router-dom";
import { useAuthContext } from "../context/AuthContext";

export default function PrivateRouterFilter() {
  const { isAuthenticated } = useAuthContext()
  return isAuthenticated ? <Outlet/> : <Navigate to="/auth/login" />
}