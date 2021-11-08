const inputCEP = document.querySelector('.input-cep');

const cep = document.querySelector('.cep');
const logradouro = document.querySelector('.logradouro');
const bairro = document.querySelector('.bairro');
const localidade = document.querySelector('.localidade');
const uf = document.querySelector('.uf');

function getCEP() {
       fetch(`https://viacep.com.br/ws/${inputCEP.value}/json`)
              .then((response) => response.json())
              .then((data) => {
                     console.log(data)
                     cep.innerText = data.cep;
                     logradouro.innerText = data.logradouro;
                     bairro.innerText = data.bairro;
                     localidade.innerText = data.localidade;
                     uf.innerText = data.uf;

              })
              .catch((erro) => console.log(erro));
}
