import NavBar from "../../components/NavBar";
import UserInfo from "../../components/UserInfo";
import PostFeed from "./PostFeed";

export default function PageHome() {
  return (
    <div className="pb-3">
      <NavBar/>
      <section className=" max-w-screen-2xl mx-auto grid grid-cols-7 gap-5 justify-center">
        <aside className="hidden lg:block col-span-2 sticky top-[82px] self-start">
          <UserInfo id={1}/>
        </aside>
        <main className="col-span-3 px-3 lg:px-0 max-w-2xl w-fullpx-auto grid gap-10">
          <PostFeed/>
          <PostFeed/>
        </main>
        <aside className="hidden col-span-2 lg:flex flex-col max-w-xs gap-4 sticky top-[82px] self-start">
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