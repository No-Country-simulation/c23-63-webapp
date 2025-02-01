import { useEffect, useState } from "react";
import IconNewProject from "../../assets/Icons/IconNewProject"
import { getPostsByProfileId } from "../../services/profileService"

export default function PostsProfile({id , setShowCreateForm}){
  const [posts, setPosts] = useState([]);
  const [countPosts, setCountPosts] = useState(0);
  const [loading, setLoading] = useState(true);

  useEffect(()=>{
    const getData = async () => {
      try {
        const { posts, countPosts } = await getPostsByProfileId(id);
        console.log(posts)
        setPosts(posts);
        setCountPosts(countPosts);
      } catch (error) {
        console.error('Error al obtener los posts:', error);
      } finally {
        setLoading(false);
      }
    }

    getData()
  },[id])

  if(loading){
    return <p>Cargando...</p>
  }

  return(
    <>
      <header className="flex gap-2">
        {
          countPosts 
            ? <p className="paragraph">{countPosts} publicaciones</p>
            : <p className="paragraph">No hay publicaciones</p>
        }
      </header>
      <section className="grid gap-3 md:grid-cols-2 lg:grid-cols-3">
        <button 
          className="bg-primary-800 rounded-xl col-span-1 flex flex-col gap-2 justify-center items-center"
          onClick={()=>{setShowCreateForm(true)}}
        >
          <IconNewProject/>
          <span className="paragraph">Agregar nuevo post</span>
        </button>
        {
          posts && posts.map((post, index) => (
            <article key={index} className="">
              <figure>
                <img src={post.description.imageUrl} alt="Post"
                  className="w-full aspect-3/2 object-cover rounded-xl"
                />
              </figure>
              <section className="bg-primary-800 -mt-4 pt-8 p-4 rounded-xl min-h-24 grid items-center">
                <p className="paragraph">
                  {post.description.title}
                </p>
                {
                  post.category && <p className="paragraph-s">{post.category}</p>
                }
              </section>
            </article>
          ))
        }
      </section>
    </>
  )
}