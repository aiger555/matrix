// let currentMatrixType = '2D';
//
// function setMatrixType(type) {
//     currentMatrixType = type;
//     document.querySelectorAll('.btn-type').forEach(btn => {
//         btn.classList.toggle('active', btn.textContent.includes(type));
//     });
//     generateMatrixInputs();
// }
//
// function generateMatrixInputs() {
//     const sizes = currentMatrixType === '2D' ? { rows: 3, cols: 3 } : { rows: 3, cols: 3, depth: 3 };
//
//     ['A', 'B', 'C', 'D'].forEach(matrix => {
//         const container = document.getElementById(`matrix${matrix}-input`);
//         if (!container) return;
//
//         container.innerHTML = '';
//
//         if (currentMatrixType === '2D') {
//             // Генерация 2D полей ввода
//             for (let i = 0; i < sizes.rows; i++) {
//                 const rowDiv = document.createElement('div');
//                 rowDiv.className = 'matrix-row';
//                 for (let j = 0; j < sizes.cols; j++) {
//                     const input = document.createElement('input');
//                     input.type = 'number';
//                     input.placeholder = '0';
//                     input.name = `matrix${matrix}[${i}][${j}]`;
//                     rowDiv.appendChild(input);
//                 }
//                 container.appendChild(rowDiv);
//             }
//         } else {
//             // Генерация 3D полей ввода
//             for (let k = 0; k < sizes.depth; k++) {
//                 const layerDiv = document.createElement('div');
//                 layerDiv.className = 'matrix-layer';
//                 layerDiv.innerHTML = `<strong>Layer ${k + 1}:</strong>`;
//                 for (let i = 0; i < sizes.rows; i++) {
//                     const rowDiv = document.createElement('div');
//                     rowDiv.className = 'matrix-row';
//                     for (let j = 0; j < sizes.cols; j++) {
//                         const input = document.createElement('input');
//                         input.type = 'number';
//                         input.placeholder = '0';
//                         input.name = `matrix${matrix}[${k}][${i}][${j}]`;
//                         rowDiv.appendChild(input);
//                     }
//                     layerDiv.appendChild(rowDiv);
//                 }
//                 container.appendChild(layerDiv);
//             }
//         }
//     });
// }
//
// // Базовые операции с двумя матрицами
// async function performOperation(operation) {
//     const formData = new FormData();
//
//     // Сбор данных матриц A и B
//     collectMatrixData(formData, 'A');
//     collectMatrixData(formData, 'B');
//
//     formData.append('operation', operation);
//
//     try {
//         const response = await fetch('/calculate/binary', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded',
//             },
//             body: new URLSearchParams(formData)
//         });
//
//         const result = await response.json();
//         displayResult(result);
//     } catch (error) {
//         console.error('Error:', error);
//         showError('Network error: ' + error.message);
//     }
// }
//
// // Операции с одной матрицей
// async function performSingleOperation(operation, matrixName) {
//     const formData = new FormData();
//
//     // Сбор данных указанной матрицы
//     collectMatrixData(formData, matrixName);
//
//     formData.append('operation', operation);
//
//     try {
//         const response = await fetch('/calculate/single', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded',
//             },
//             body: new URLSearchParams(formData)
//         });
//
//         const result = await response.json();
//         displayResult(result);
//     } catch (error) {
//         console.error('Error:', error);
//         showError('Network error: ' + error.message);
//     }
// }
//
// // Операции с тремя матрицами
// async function performTripleOperation(operation) {
//     const formData = new FormData();
//
//     // Сбор данных матриц A, B, C
//     collectMatrixData(formData, 'A');
//     collectMatrixData(formData, 'B');
//     collectMatrixData(formData, 'C');
//
//     formData.append('operation', operation);
//
//     try {
//         const response = await fetch('/calculate/triple', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded',
//             },
//             body: new URLSearchParams(formData)
//         });
//
//         const result = await response.json();
//         displayResult(result);
//     } catch (error) {
//         console.error('Error:', error);
//         showError('Network error: ' + error.message);
//     }
// }
//
// // Операции с четырьмя матрицами
// async function performQuadrupleOperation(operation) {
//     const formData = new FormData();
//
//     // Сбор данных матриц A, B, C, D
//     collectMatrixData(formData, 'A');
//     collectMatrixData(formData, 'B');
//     collectMatrixData(formData, 'C');
//     collectMatrixData(formData, 'D');
//
//     formData.append('operation', operation);
//
//     try {
//         const response = await fetch('/calculate/quadruple', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded',
//             },
//             body: new URLSearchParams(formData)
//         });
//
//         const result = await response.json();
//         displayResult(result);
//     } catch (error) {
//         console.error('Error:', error);
//         showError('Network error: ' + error.message);
//     }
// }
//
// // Скалярное умножение
// async function performScalarMultiply(matrixName) {
//     const scalarValue = document.getElementById('scalarValue').value;
//     if (!scalarValue) {
//         showError('Please enter a scalar value');
//         return;
//     }
//
//     const formData = new FormData();
//     collectMatrixData(formData, matrixName);
//
//     formData.append('scalar', scalarValue);
//
//     try {
//         const response = await fetch('/scalar/multiply', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded',
//             },
//             body: new URLSearchParams(formData)
//         });
//
//         const result = await response.json();
//         displayResult(result);
//     } catch (error) {
//         console.error('Error:', error);
//         showError('Network error: ' + error.message);
//     }
// }
//
// // Генерация специальных матриц
// async function generateMatrix(type, rows, cols) {
//     try {
//         const response = await fetch('/generate/matrix', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded',
//             },
//             body: new URLSearchParams({
//                 type: type,
//                 rows: rows,
//                 cols: cols
//             })
//         });
//
//         const result = await response.json();
//         displayResult(result);
//     } catch (error) {
//         console.error('Error:', error);
//         showError('Network error: ' + error.message);
//     }
// }
//
// // Взвешенная сумма (модальное окно)
// function showWeightedSumModal() {
//     const weights = prompt('Enter weights for A, B, C, D (comma separated):', '1,1,1,1');
//     if (weights) {
//         const weightArray = weights.split(',').map(w => parseFloat(w.trim()));
//         if (weightArray.length === 4 && weightArray.every(w => !isNaN(w))) {
//             performWeightedSum(weightArray[0], weightArray[1], weightArray[2], weightArray[3]);
//         } else {
//             showError('Please enter 4 valid numbers separated by commas');
//         }
//     }
// }
//
// async function performWeightedSum(w1, w2, w3, w4) {
//     const formData = new FormData();
//
//     collectMatrixData(formData, 'A');
//     collectMatrixData(formData, 'B');
//     collectMatrixData(formData, 'C');
//     collectMatrixData(formData, 'D');
//
//     formData.append('operation', 'weightedSum');
//     formData.append('w1', w1);
//     formData.append('w2', w2);
//     formData.append('w3', w3);
//     formData.append('w4', w4);
//
//     try {
//         const response = await fetch('/calculate/quadruple', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded',
//             },
//             body: new URLSearchParams(formData)
//         });
//
//         const result = await response.json();
//         displayResult(result);
//     } catch (error) {
//         console.error('Error:', error);
//         showError('Network error: ' + error.message);
//     }
// }
//
// // Вспомогательная функция для сбора данных матрицы
// function collectMatrixData(formData, matrixName) {
//     const inputs = document.querySelectorAll(`input[name^="matrix${matrixName}["]`);
//     inputs.forEach(input => {
//         if (input.value !== '') {
//             formData.append(input.name, input.value);
//         }
//     });
// }
//
// // Отображение результатов
// function displayResult(data) {
//     const resultSection = document.getElementById('result-section');
//     const resultMatrix = document.getElementById('result-matrix');
//     const errorDiv = document.getElementById('error-message');
//
//     // Скрыть ошибки
//     if (errorDiv) {
//         errorDiv.style.display = 'none';
//     }
//
//     if (data.success) {
//         resultSection.style.display = 'block';
//
//         if (data.type === 'scalar') {
//             // Для скалярных результатов (determinant, trace)
//             resultMatrix.innerHTML = `<div class="scalar-result">
//                 <h4>Result: ${data.result.toFixed(4)}</h4>
//             </div>`;
//         } else {
//             // Для матричных результатов
//             resultMatrix.innerHTML = formatMatrixForDisplay(data.result);
//         }
//     } else {
//         showError(data.error);
//     }
// }
//
// function formatMatrixForDisplay(matrix) {
//     if (!matrix) return 'No result data';
//
//     if (matrix.type === '2D' && matrix.data2D) {
//         return matrix.data2D.map(row =>
//             `[ ${row.map(val => typeof val === 'number' ? val.toFixed(2) : val).join(', ')} ]`
//         ).join('<br>');
//     } else if (matrix.type === '3D' && matrix.data3D) {
//         return matrix.data3D.map((layer, idx) =>
//             `<div class="matrix-layer"><strong>Layer ${idx + 1}:</strong><br>` +
//             layer.map(row =>
//                 `[ ${row.map(val => typeof val === 'number' ? val.toFixed(2) : val).join(', ')} ]`
//             ).join('<br>') + '</div>'
//         ).join('<br>');
//     } else {
//         return 'Invalid matrix data';
//     }
// }
//
// function showError(message) {
//     const resultSection = document.getElementById('result-section');
//     const resultMatrix = document.getElementById('result-matrix');
//
//     resultSection.style.display = 'block';
//     resultMatrix.innerHTML = `<div class="error-message" style="color: red; background: #f8d7da; padding: 10px; border-radius: 5px;">
//         <strong>Error:</strong> ${message}
//     </div>`;
// }
//
// // Инициализация
// document.addEventListener('DOMContentLoaded', function() {
//     generateMatrixInputs();
//
//     // Добавляем обработчики для базовых операций
//     document.querySelectorAll('.btn-basic-operation').forEach(btn => {
//         btn.addEventListener('click', function() {
//             const operation = this.getAttribute('data-operation');
//             performOperation(operation);
//         });
//     });
// });