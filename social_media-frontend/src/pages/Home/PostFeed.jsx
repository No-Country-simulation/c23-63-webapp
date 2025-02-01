export default function PostFeed() {
  return(
    <article className="w-full">
      <header className="w-full flex gap-3 items-center py-3">
        <img src="https://avatars.githubusercontent.com/u/1?v=4" 
          className="w-10 rounded-full"
        />
        <h4 className="name-post">User Name</h4>
      </header>
      <figure>
        <img src="https://cdn-front.freepik.com/images/ai/image-generator/gallery/resource-tti-16.webp"
          className="w-full aspect-4/3 object-cover rounded-xl"
        />
      </figure>
      <section className="bg-primary-800 rounded-xl -mt-5 p-4 pt-9 flex justify-between items-center">
        <div>
          <p className="title-post">Title post</p>
          <p className="tag-post">css-js-react</p>
        </div>
        <div className="pr-3">
          <span>120 Likes ❤</span>
        </div>
      </section>
    </article>
  )
}