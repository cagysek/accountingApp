/**
    Add rows to invoice form
 */
function addRow(){
    let listName = 'invoiceItems';
    let fieldsNames = ['id', 'name', 'quantity', 'priceVat', 'price', 'priceDph'];
    let rowIndex = document.querySelectorAll('.item').length;

    let row = document.createElement('div');
    row.classList.add('row', 'item');

    fieldsNames.forEach((fieldName) => {
        let col = document.createElement('div');
        col.classList.add('col', 'form-group');
        if (fieldName === 'id') {
            col.classList.add('d-none'); //field with id - hidden
        }

        let input = document.createElement('input');
        input.type = 'text';
        input.classList.add('form-control');
        input.id = listName + rowIndex + '.' + fieldName;
        input.setAttribute('name',listName + '[' + rowIndex + '].' + fieldName);

        col.appendChild(input);
        row.appendChild(col);
    });

    let deleteButton = document.createElement('a');
    deleteButton.classList.add('btn');
    deleteButton.classList.add('btn-danger');
    deleteButton.classList.add('remove');
    deleteButton.setAttribute('onclick', 'removeInputField(this);');
    deleteButton.setAttribute('role', 'button');
    deleteButton.text ='X';
    row.appendChild(deleteButton);
    document.getElementById('invoiceItems').appendChild(row);

    return false;
};

/**
 * Remove row from invoice form
 * @param selectedField
 */
function removeInputField (selectedField) {
    selectedField.closest('.row').remove();
};