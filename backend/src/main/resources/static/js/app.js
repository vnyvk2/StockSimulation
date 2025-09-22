// frontend/js/app.js
const API = "http://localhost:8080/api";
function setMsg(m){ document.getElementById('msg')?.innerText = m || ""; }

async function register(){
  const username = document.getElementById('regUsername').value;
  const password = document.getElementById('regPassword').value;
  const res = await fetch(`${API}/auth/register`,{
    method:'POST', headers:{'Content-Type':'application/json'},
    body: JSON.stringify({username,password})
  });
  if(res.ok){ setMsg("Registered. You can login."); }
  else setMsg("Register failed");
}

async function login(){
  const username = document.getElementById('loginUsername').value;
  const password = document.getElementById('loginPassword').value;
  const res = await fetch(`${API}/auth/login`,{
    method:'POST', headers:{'Content-Type':'application/json'},
    body: JSON.stringify({username,password})
  });
  if(res.ok){
    const user = await res.json();
    localStorage.setItem('user', JSON.stringify(user));
    window.location.href = 'dashboard.html';
  } else {
    setMsg("Login failed");
  }
}

function logout(){ localStorage.removeItem('user'); window.location.href='index.html'; }

async function loadDashboard(){
  const user = JSON.parse(localStorage.getItem('user')||'null');
  if(!user){ window.location.href='index.html'; return; }
  document.getElementById('userInfo').innerText = `User: ${user.username} | Cash: ₹${user.cash.toFixed(2)}`;

  const res = await fetch(`${API}/stocks`);
  const stocks = await res.json();
  const tbody = document.querySelector('#stocksTable tbody');
  tbody.innerHTML = "";
  stocks.forEach(s=>{
    const tr = document.createElement('tr');
    tr.innerHTML = `<td>${s.symbol}</td><td>${s.name}</td><td>${s.price}</td>
      <td><a href="trade.html?symbol=${s.symbol}">Trade</a></td>`;
    tbody.appendChild(tr);
  });

  // For simplicity portfolio data retrieval omitted; you can call /api/portfolio endpoints if implemented
}

function onTradePageLoad(){
  const user = JSON.parse(localStorage.getItem('user')||'null');
  if(!user){ window.location.href='index.html'; return; }
  const params = new URLSearchParams(location.search);
  const symbol = params.get('symbol') || '';
  document.getElementById('symbol').value = symbol;
}

async function buy(){
  const user = JSON.parse(localStorage.getItem('user')||'null');
  const symbol = document.getElementById('symbol').value;
  const qty = Number(document.getElementById('qty').value);
  const res = await fetch(`${API}/trades/buy`,{
    method:'POST', headers:{'Content-Type':'application/json'},
    body: JSON.stringify({userId:user.id, symbol, quantity: qty})
  });
  if(res.ok){ document.getElementById('tradeMsg').innerText = 'Buy executed'; }
  else { const err = await res.text(); document.getElementById('tradeMsg').innerText = 'Error: '+err; }
}

async function sell(){
  const user = JSON.parse(localStorage.getItem('user')||'null');
  const symbol = document.getElementById('symbol').value;
  const qty = Number(document.getElementById('qty').value);
  const res = await fetch(`${API}/trades/sell`,{
    method:'POST', headers:{'Content-Type':'application/json'},
    body: JSON.stringify({userId:user.id, symbol, quantity: qty})
  });
  if(res.ok){ document.getElementById('tradeMsg').innerText = 'Sell executed'; }
  else { const err = await res.text(); document.getElementById('tradeMsg').innerText = 'Error: '+err; }
}

// Auto-run hooks
if(document.body && location.pathname.endsWith('dashboard.html')) loadDashboard();
if(document.body && location.pathname.endsWith('trade.html')) onTradePageLoad();
