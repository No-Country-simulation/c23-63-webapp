import NavBar from "../../components/NavBar";
import UserInfo from "../../components/UserInfo";
import { useAuthContext } from "../../context/AuthContext";
import PostFeed from "./PostFeed";

export default function PageHome() {
  const  { logout } = useAuthContext()
  return (
    <div className="pb-3">
      <NavBar/>
      <section className=" max-w-screen-xl mx-auto md:grid grid-cols-7 gap-4 justify-center">
        <aside className="hidden md:flex flex-col col-span-2 px-2 sticky top-[82px] justify-between h-[calc(100vh-94px)] ">
          <article>
            <UserInfo id={1} isFeed={true}/>
          </article>
          <section>
            <button 
              onClick={logout}
              className="w-full p-4 rounded-xl bg-primary-800"
            >
              logout
            </button>
          </section>
        </aside>
        <main className="w-full lg:max-w-2xl lg:col-span-3 md:col-span-5 grid gap-10 px-3 lg:px-0 px-auto ">
          <PostFeed/>
          <PostFeed/>
          <PostFeed/>
          <PostFeed/>
          <PostFeed/>
          <PostFeed/>
        </main>
        <aside className="hidden lg:flex flex-col col-span-2 max-w-xs gap-4 px-2 sticky top-[82px] self-start">
          <article className=" w-full bg-primary-800 rounded-2xl">
            <h3 className="title p-3">Amigos sugeridos</h3>
            <ul>
              <li>
                <article className="flex gap-2 items-center p-3">
                  <img src="https://avatars.githubusercontent.com/u/1?v=4" 
                    className="w-10 rounded-full"
                  />
                  <div className="grid">
                    <strong>User Name</strong>
                    <span className="paragraph-s text-neutral-400">Job Title</span>
                  </div>
                </article>
              </li>
            </ul>
          </article>
        </aside>
      </section>
    </div>
  )
}