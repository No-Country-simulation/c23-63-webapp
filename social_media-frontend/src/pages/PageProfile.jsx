import { useParams } from "react-router-dom"
import { getPostsByProfileId } from "../services/profileService"
import NavBar from "../components/NavBar"
import UserInfo from "../components/UserInfo"

export default function PageProfile() {

  const { id } = useParams()
  const {posts, count_posts} = getPostsByProfileId(id)

  return (
    <div className="max-w-[1200px] lg:mx-auto grid gap-4 justify-center grid-cols-1 pb-3">
      <NavBar />
      <UserInfo id={id}/>
      <main className="px-3 lg:px-0">
        <header className="flex gap-2">
          {
            count_posts 
              ? <p className="paragraph">{count_posts} publicaciones</p>
              : <p className="paragraph">No hay publicaciones</p>
          }
        </header>
        <section className="grid grid-cols-2 gap-3 lg:grid-cols-3 lg:max-w-6xl ">
          {
            posts && posts.map((post, index) => (
              <article key={index}>
                <picture>
                  <img src={post.description.imageUrl} alt="Post"
                    className="aspect-3/2 object-cover rounded-xl"
                  />
                </picture>
                <p>{post.description.title}</p>
                <p>{post.tags}</p>
              </article>
            ))
          }
        </section>
      </main>
    </div>
  )
}