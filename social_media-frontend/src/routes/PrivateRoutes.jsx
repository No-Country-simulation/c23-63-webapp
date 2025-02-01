import { Route } from "react-router-dom";
import PageHome from "../pages/home/PageHome";
import PrivateRouterFilter from "./PrivateRouterFilter";

export function PrivateRoutes() {
  return (
    <Route element={<PrivateRouterFilter/>}>
      <Route path="/" element={<PageHome/>}/>
    </Route>
  )
}