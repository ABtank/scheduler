(window.webpackJsonp=window.webpackJsonp||[]).push([[5],{277:function(t,e,r){"use strict";r.r(e);var l={name:"NuxtTutorial"},n=r(45),component=Object(n.a)(l,(function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"relative flex items-top justify-center min-h-screen bg-gray-100 sm:items-center sm:pt-0"},[r("link",{attrs:{href:"https://cdn.jsdelivr.net/npm/tailwindcss@2.1.2/dist/tailwind.min.css",rel:"stylesheet"}}),t._v(" "),r("div",{staticClass:"max-w-4xl mx-auto sm:px-6 lg:px-8"},[r("a",{staticClass:"text-2xl leading-7 font-semibold flex justify-center pt-8 sm:pt-0",attrs:{href:"http://localhost:8189/sh/swagger-ui/",target:"_blank"}},[t._v("\n      Scheduler\n    ")]),t._v(" "),t._m(0),t._v(" "),r("div",{staticClass:"flex justify-center pt-4 space-x-2"},[r("a",{attrs:{href:"https://github.com/nuxt/nuxt.js",target:"_blank"}},[r("svg",{staticClass:"w-6 h-6 text-gray-600 hover:text-gray-800 button--github",attrs:{xmlns:"http://www.w3.org/2000/svg","xmlns:xlink":"http://www.w3.org/1999/xlink","aria-hidden":"true",role:"img",width:"32",height:"32",preserveAspectRatio:"xMidYMid meet",viewBox:"0 0 24 24"}},[r("path",{attrs:{d:"M12 2.247a10 10 0 0 0-3.162 19.487c.5.088.687-.212.687-.475c0-.237-.012-1.025-.012-1.862c-2.513.462-3.163-.613-3.363-1.175a3.636 3.636 0 0 0-1.025-1.413c-.35-.187-.85-.65-.013-.662a2.001 2.001 0 0 1 1.538 1.025a2.137 2.137 0 0 0 2.912.825a2.104 2.104 0 0 1 .638-1.338c-2.225-.25-4.55-1.112-4.55-4.937a3.892 3.892 0 0 1 1.025-2.688a3.594 3.594 0 0 1 .1-2.65s.837-.262 2.75 1.025a9.427 9.427 0 0 1 5 0c1.912-1.3 2.75-1.025 2.75-1.025a3.593 3.593 0 0 1 .1 2.65a3.869 3.869 0 0 1 1.025 2.688c0 3.837-2.338 4.687-4.563 4.937a2.368 2.368 0 0 1 .675 1.85c0 1.338-.012 2.413-.012 2.75c0 .263.187.575.687.475A10.005 10.005 0 0 0 12 2.247z",fill:"currentColor"}})])]),t._v(" "),r("a",{attrs:{href:"https://twitter.com/nuxt_js",target:"_blank"}},[r("svg",{staticClass:"w-6 h-6 text-gray-600 hover:text-gray-800",attrs:{xmlns:"http://www.w3.org/2000/svg","xmlns:xlink":"http://www.w3.org/1999/xlink","aria-hidden":"true",role:"img",width:"32",height:"32",preserveAspectRatio:"xMidYMid meet",viewBox:"0 0 24 24"}},[r("path",{attrs:{d:"M22.46 6c-.77.35-1.6.58-2.46.69c.88-.53 1.56-1.37 1.88-2.38c-.83.5-1.75.85-2.72 1.05C18.37 4.5 17.26 4 16 4c-2.35 0-4.27 1.92-4.27 4.29c0 .34.04.67.11.98C8.28 9.09 5.11 7.38 3 4.79c-.37.63-.58 1.37-.58 2.15c0 1.49.75 2.81 1.91 3.56c-.71 0-1.37-.2-1.95-.5v.03c0 2.08 1.48 3.82 3.44 4.21a4.22 4.22 0 0 1-1.93.07a4.28 4.28 0 0 0 4 2.98a8.521 8.521 0 0 1-5.33 1.84c-.34 0-.68-.02-1.02-.06C3.44 20.29 5.7 21 8.12 21C16 21 20.33 14.46 20.33 8.79c0-.19 0-.37-.01-.56c.84-.6 1.56-1.36 2.14-2.23z",fill:"currentColor"}})])])])])])}),[function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"mt-8 bg-white overflow-hidden shadow sm:rounded-lg p-6"},[r("h2",{staticClass:"text-2xl leading-7 font-semibold"},[t._v("\n        Welcome to Scheduler Application\n      ")]),t._v(" "),r("p",{staticClass:"mt-3 text-gray-600"},[t._v("\n        Приложение "),r("a",{attrs:{target:"_blank",href:"http://localhost:8189/sh/swagger-ui/"}},[t._v("Sheduler")]),t._v("\n        для составления/корректировки расписания индивидуальных занятий(уроков) преподавателей/тренеров с учениками/клиентами."),r("br")]),t._v(" "),r("p",{staticClass:"mt-4 pt-4 text-gray-800 border-t border-dashed"},[r("b",[t._v("На примере учеников муз школы")]),t._v(" : у преподавателя есть временной интервал,\n        когда он работает, в начале года допустим все ученики выбирают удобные для них интервалы времени.\n      ")]),t._v(" "),r("p",{staticClass:"mt-4 pt-4 text-gray-800 border-t border-dashed"},[t._v("\n        Затем если выбранные интервалы не накладываются друг на друга, то это время автоматически закрепляется за учеником.\n      ")]),t._v(" "),r("p",{staticClass:"mt-4 pt-4 text-gray-800 border-t border-dashed"},[t._v("\n        В противном случае, преподаватель сам корректирует накладки, возможно самим доработать алгоритм для оптимизации составления этого расписания.\n      ")]),t._v(" "),r("p",{staticClass:"mt-4 pt-4 text-gray-800 border-t border-dashed"},[r("b",[t._v("А после утверждения расписания, сделать возможным следующее:")]),t._v("\n        Если ученик заболел/пропускает занятие, то он в приложении уведомляет об этом - а в освободившееся окно могут записаться другие.\n      ")]),t._v(" "),r("p",{staticClass:"mt-4 pt-4 text-gray-800 border-t border-dashed"},[t._v("\n        Также если кто-то хочет время поменять , то в приложении всех учеников можно оповестить - вдруг кто-то может поменяться.\n      ")]),t._v(" "),r("p",{staticClass:"mt-4 pt-4 text-gray-800 border-t border-dashed"},[r("b",[t._v("Обычно эта работа отнимает много времени у педагогов , которые ведут индивидуальные занятия.")])])])}],!1,null,null,null);e.default=component.exports}}]);