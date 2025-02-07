import { useEffect, useRef, useState } from "react";
import { createPost } from "../../services/postsService";
import IconCLose from "../../assets/Icons/IconClose";
import IconMedia from "../../assets/Icons/IconMedia";
import { useAuthContext } from "../../context/AuthContext";

export default function CreateNewPost ({ setShowCreateForm }){
  const { isAuthenticated } = useAuthContext()

  const titleRef = useRef()

  if (!isAuthenticated) setShowCreateForm(false)

  const [image, setImage] = useState(null)
  const [title, setTitle] = useState('')
  const [tags, setTags] = useState([])
  const [content, setContent] = useState('')
  const [preview, setPreview] = useState('')

  useEffect(() => {
    titleRef.current.focus()
  }, [])

  const handleTagsChange = (tags) => {
    const formattedTags = tags
      .split(/[ ,]+/)
      .filter(tag => tag.trim() !== "")
      .map(tag => tag.trim())
    setTags(formattedTags)
  };
    
  const handleImageChange = (files) => {
    const file = files[0]

    if (file) {
      if (file.type.startsWith("image/")) {
        const imageUrl = URL.createObjectURL(file);
        setPreview(imageUrl);
        setImage(file)
      } else {
        alert("Por favor, selecciona un archivo de imagen válido.");
        setImage(null);
        setPreview("");
      }
    }
  };

  const clearPreview = () => {
    setPreview('')
    setImage(null)    
  }

  const handleSubmit = async (event) => {
    event.preventDefault()

    if (!image) {
      alert("Por favor, selecciona una imagen")
      return
    }
    
    const formData = new FormData()
    formData.append('userId', 3 )
    formData.append('title', title)
    formData.append('content', content)
    formData.append('file', image)
    formData.append('category', JSON.stringify(tags))
    
    try {
      const response = await createPost(formData)
      console.log("Imagen subida:", response)
      alert("succes")
      setShowCreateForm(false)
    } catch (error) {
      console.error("Error al subir la imagen:", error)
    }
  };

  return (
    <>
      <h2 className="h1-pop font-bold mb-3">Crear nueva publicación</h2>
      <form onSubmit={handleSubmit} className="flex flex-col gap-3">
        <div className="grid md:grid-cols-2 lg:grid-cols-5 gap-4">
          <section className='lg:col-span-3 w-full aspect-3/2 rounded-xl bg-primary-100/30'>
            {
              preview ? (
                <div className='relative'>
                  <div className="relative w-full aspect-3/2">
                    <img
                      src={preview}
                      alt="Vista previa"
                      className="w-full aspect-3/2 object-cover rounded-xl"
                    />
                    <div className="absolute inset-0 bg-neutral-950/25 pointer-events-none rounded-xl"></div>
                  </div>
                  <button type="button" onClick={clearPreview} className='absolute z-[2] top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2'>
                    <IconCLose className='w-10 h-10'/>
                  </button>
                </div>
              )
              : (
                <div className='w-full h-full flex flex-col justify-center gap-4 items-center'>
                  <IconMedia />
                  <label htmlFor="file-input" className="flex bg-primary-200 p-3 rounded-2xl text-16 font-medium font-popins">
                    <span className="px-5">Seleccionar imagen</span>
                    <input 
                      id="file-input"
                      className='hidden'
                      type="file"
                      accept="image/*" 
                      onChange={(e) => handleImageChange(e.target.files)}
                    />
                  </label>
                </div>
              )
            }
            <img/>          
          </section>

          <section className="lg:col-span-2 flex gap-4 flex-col">
            <input
              className="input"
              type="text"
              placeholder="Título"
              onChange={({target})=> setTitle(target.value)}
              value={title}
              ref={titleRef}
              required
            />

            <input 
              className="input"
              type="text"
              placeholder="Categoría"
              onChange={(e)=> handleTagsChange(e.target.value)}
              maxLength="50" 
              required
            />
            <div className="text-sm flex gap-1 items-center paragraph-xs flex-wrap">
              Categorías:
              {
                tags.map((tag, index) => (
                  <span key={index} className="bg-primary-200 px-2.5 py-0.5 rounded-md">{tag}</span>
                ))
              }
            </div>
            <div className='relative'>
              <textarea
                className="input resize-none"
                placeholder="Contenido"
                value={content}
                onChange={({target}) => setContent(target.value)}
                rows="5"
                maxLength="100"
                required
              />
              <span className=" text-primary-800 paragraph-s z-[2] absolute right-3 bottom-3 ">
                {content.length}
              </span>
            </div>        
          </section>
        </div>

        <section className='bg-primary-600 w-full flex gap-3'>
          <button type="button" onClick={() => setShowCreateForm()} className="button-border flex-1">
            Cancelar
          </button>
          <button type="submit" className="button-filled flex-1">
            Publicar
          </button>
        </section>
      </form>
    </>
  );
}