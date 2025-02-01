import { IsotipoNexo } from "../../assets/Icons/Nexo";
import PropTypes from 'prop-types';

export default function BaseForm({title, children, onSubmit}) {
  return(
    <>
      <header className="grid gap-8 justify-items-center">
        <IsotipoNexo className='w-28' />
        {
          title && <p className="text-4xl ">{title}</p>
        }
      </header>
      <form onSubmit={onSubmit} className="flex flex-col flex-grow items-start justify-center gap-4">
        {children}
      </form>
    </>
  )
}

BaseForm.propTypes = {
  title: PropTypes.string,
  children: PropTypes.node.isRequired,
  onSubmit: PropTypes.func.isRequired,
};