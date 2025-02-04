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
        'primary-400': '#5F3F84',
        'primary-600': '#462E62',
        'primary-800': '#1C1028'
      },
      fontFamily: {
        'popins': ['Poppins', 'sans-serif'],
        'rubik': ['Rubik', 'sans-serif'],
        'sen': ['Sen', 'sans-serif'],
      },
      fontSize: {
        '24' : '24px',
        '20' : '20px',
        '16' : '16px',
        '14' : '14px',
        '12' : '12px',
      },
      fontWeight: {
        'bold': 700,
        'medium': 500,
        'regular': 400,
        'thin': 300,
      },
      aspectRatio:{
        '2/1':'2/1',
        '3/2': '3/2',
        '4/3': '4/3'
      }
    },
    
  },
  plugins: [],
};

