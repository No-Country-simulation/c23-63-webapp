import { Routes } from "react-router-dom";
import AuthProvider from "../context/AuthProvider";
import { PublicRoutes } from "./PublicRoutes";
import { PrivateRoutes } from "./PrivateRoutes";

export default function AppRoutes () {
  return (
    <AuthProvider>
      <Routes>
        {PublicRoutes()}
        {PrivateRoutes()}
      </Routes>
  </AuthProvider>
  )
}