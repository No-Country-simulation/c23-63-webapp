import { Link } from "react-router-dom";
import { getPostsFeed } from "../../services/postsService";

export default function PostsFeed() {

  const {posts} = getPostsFeed()

  return(
    <main className="w-full lg:max-w-2xl lg:col-span-3 md:col-span-5 grid lg:gap-8 gap-2 px-3 lg:px-0 px-auto ">    
      {
        posts && posts.map((post)=> (
          <article key={post.postId} className="w-full">
            <Link to={`/profile/${post.userData.userId}`}>
              <header className="w-full flex gap-3 items-center py-3">
                <img src={post.userData.avatarUrl}
                  className="w-10 rounded-full"
                />
                <h4 className="name-post">{post.userData.userName}</h4>
              </header>
            </Link>
            <Link to={`/post/${post.postId}`}>
              <figure>
                <img src={post.content.imageUrl}
                  className="w-full aspect-4/3 object-cover rounded-xl"
                />
              </figure>
              <section className="bg-primary-800 rounded-xl -mt-5 p-4 pt-9 flex justify-between items-center">
                <div >
                  <p className="title-post">{post.content.title}</p>
                  <div className="flex gap-2 paragraph-xs mt-2">
                    {
                      post.category?.map((cat)=>(
                        <span key={cat} className="px-2 py-1 bg-primary-200 rounded-md">{cat}</span>
                      )) 
                    }
                  </div>
                </div>
                <div className="pr-3">
                  <span>120 Likes ❤</span>
                </div>
              </section>
            </Link>
          </article>
        ))
      }
    </main>
  )
}