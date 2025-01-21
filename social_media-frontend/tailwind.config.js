/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'primary-100': '#9766D3',
        'primary-200': '#8B5CC3',
        'primary-600': '#462E62',
      },
      fontFamily: {
        'popins': ['Poppins', 'sans-serif'],
        'rubik': ['Rubik', 'sans-serif'],
        'sen': ['Sen', 'sans-serif'],
      },
      fontSize: {
        '22' : '22px',
        '19' : '19px',
        '16' : '16px',
        '14' : '14px',
        '12' : '12px',
      },
      fontWeight: {
        'bold': 700,
        'medium': 500,
        'regular': 400,
      }
    },
    
  },
  plugins: [],
};

