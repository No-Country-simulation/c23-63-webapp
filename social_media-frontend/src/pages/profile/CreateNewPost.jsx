import { useEffect, useRef, useState } from "react";
import { createPost } from "../../services/posts";

export default function CreateNewPost ({ setShowCreateForm }){
  const titleRef = useRef()

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

  const handleSubmit = async (event) => {
    event.preventDefault()

    if (!image) {
      alert("Por favor, selecciona una imagen")
      return
    }
    
    const formData = new FormData()
    formData.append("image", image)
    formData.append("title", title)
    formData.append("category", JSON.stringify(tags))
    
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
      <button onClick={()=>{setShowCreateForm(false)}} className="text-red-300 bg-red-950 p-3 rounded-full">
        Cerrar
      </button>
      <h2 className="text-xl font-bold">Crear nueva publicación</h2>
      <form onSubmit={handleSubmit} className="space-y-3 p-3">
        <div className="flex gap-4">
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
          />
        </div>
        <div className="mt-2 text-sm">
          Tags: 
          {
            tags.map((tag, index) => (
              <span key={index} className="mr-2 bg-primary-200 px-2 py-1 rounded-md">{tag}</span>
            ))
          }
        </div>
        <textarea
          className="input"
          placeholder="Contenido"
          value={content}
          onChange={({target}) => setContent(target.value)}
          rows="4"
          maxLength="100" 
        />
        <p className="text-sm text-gray-600">
          {content.length}
        </p>

        <input type="file" accept="image/*" onChange={(e) => handleImageChange(e.target.files)} />

        {preview && (
          <div className="mt-2">
            <p className="text-sm text-gray-600">Vista previa:</p>
            <img
              src={preview}
              alt="Vista previa"
              className="w-full max-w-xs rounded-lg shadow-md"
            />
          </div>
        )}

        <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">
          Subir Publicación
        </button>
      </form>
    </>
  );
}